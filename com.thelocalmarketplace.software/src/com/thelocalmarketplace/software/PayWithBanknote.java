package com.thelocalmarketplace.software;

import com.tdc.CashOverloadException;
import com.tdc.DisabledException;
import com.tdc.IComponent;
import com.tdc.IComponentObserver;
import com.tdc.banknote.*;
import java.math.BigDecimal;
import java.util.Currency;

public class PayWithBanknote implements BanknoteValidatorObserver {
    private BanknoteInsertionSlot insertionSlot;
    private BanknoteValidator validator;
    private BanknoteStorageUnit storageUnit;
    private BanknoteDispenserBronze dispenser;
    private BigDecimal amountDue;
    private Currency currency;
    private boolean waitingForValidation;

    public PayWithBanknote(BanknoteInsertionSlot insertionSlot, BanknoteValidator validator,
                           BanknoteStorageUnit storageUnit, BanknoteDispenserBronze dispenser,
                           BigDecimal amountDue, Currency currency) {
        this.insertionSlot = insertionSlot;
        this.validator = validator;
        this.storageUnit = storageUnit;
        this.dispenser = dispenser;
        this.amountDue = amountDue;
        this.currency = currency;
        this.waitingForValidation = false;
        validator.attach(this);
    }

    public void payWithBanknote(Banknote banknote) throws InvalidCurrencyException {
        if (!banknote.getCurrency().equals(currency)) {
            throw new InvalidCurrencyException("Invalid currency.");
        }
        try {
			insertionSlot.receive(banknote);
		} catch (DisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CashOverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			validator.receive(banknote);
		} catch (DisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CashOverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        waitingForValidation = true;
    }

    @Override
    public void goodBanknote(BanknoteValidator validator, Currency currency, BigDecimal denomination) {
        if (!waitingForValidation) return;
        waitingForValidation = false;
        updateTransactionTotal(denomination);
        try {
            dispenseChange();
        } catch (ChangeDispenseException e) {
            // Log error or handle it appropriately
        }
    }

    @Override
    public void badBanknote(BanknoteValidator validator) {
        if (!waitingForValidation) return;
        waitingForValidation = false;
        // Handle rejected banknote scenario
    }

    private void updateTransactionTotal(BigDecimal banknoteValue) {
        amountDue = amountDue.subtract(banknoteValue);
    }

    private void dispenseChange() throws ChangeDispenseException {
        if (amountDue.compareTo(BigDecimal.ZERO) >= 0) {
            return; // No change required
        }
        BigDecimal changeAmount = amountDue.negate();
        try {
            while (changeAmount.compareTo(BigDecimal.ZERO) > 0) {
                Banknote banknoteToDispense = selectBanknoteForChange1(changeAmount);
                if (banknoteToDispense != null) {
                	// the emit() method for abstract banknote dispenser does not take any arguments
                	// the type of banknote to dispense is instead determined by which banknoteDispenser the method is called from,
                	// since different dispensers store different denominations of banknotes
                	// Dongwen
                	
                    dispenser.emit(banknoteToDispense); // Emit the banknote (Error here)
                	
                    changeAmount = changeAmount.subtract(banknoteToDispense.getDenomination());
                } else {
                    throw new InsufficientChangeException("Unable to dispense the required change.");
                }
            }
        } catch (InsufficientChangeException e) {
            throw new ChangeDispenseException("Error dispensing change: " + e.getMessage(), e);
        }
    }

    private Banknote selectBanknoteForChange1(BigDecimal changeAmount) {
    	    
    	    BigDecimal[] denominations = new BigDecimal[]{new BigDecimal("50"), new BigDecimal("20"), new BigDecimal("10"), new BigDecimal("5"), new BigDecimal("1")};

    	    for (BigDecimal denomination : denominations) {
    	        if (changeAmount.compareTo(denomination) >= 0) {
    	            return new Banknote(currency, denomination);
    	        }
    	    }
    	    return null; // No suitable denomination found
 	   
    	}
   

    @Override
    public void enabled(IComponent<? extends IComponentObserver> component) {
        System.out.println(component.getClass().getSimpleName() + " is enabled.");
    }

    @Override
    public void disabled(IComponent<? extends IComponentObserver> component) {
        System.out.println(component.getClass().getSimpleName() + " is disabled.");
    }

    @Override
    public void turnedOn(IComponent<? extends IComponentObserver> component) {
        System.out.println(component.getClass().getSimpleName() + " is turned on.");
    }

    @Override
    public void turnedOff(IComponent<? extends IComponentObserver> component) {
        System.out.println(component.getClass().getSimpleName() + " is turned off.");
    }

    class InvalidCurrencyException extends Exception {
        public InvalidCurrencyException(String message) {
            super(message);
        }
    }


    class ChangeDispenseException extends Exception {
        public ChangeDispenseException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    class InsufficientChangeException extends Exception {
        public InsufficientChangeException(String message) {
            super(message);
        }
    }
}

public class MaintainInk {
    private PredictiveAnalysis predictiveAnalysis;

    public MaintainInk() {
        this.predictiveAnalysis = new PredictiveAnalysis();
    }

    public void checkInkStatus() {
        // Use DataCollector to collect ink data
        List<InkUsageData> inkData = predictiveAnalysis.getDataCollector().collectInkUsageData();
        predictiveAnalysis.performPredictiveAnalysis(inkData);
    }
}


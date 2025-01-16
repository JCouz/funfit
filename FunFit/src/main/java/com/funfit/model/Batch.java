package com.funfit.model;

public class Batch {
	 private int batchId;
	    private String batchName;
	    private String startTime;
	    private String endTime;
	    private String dayOfWeek;
	    
	    public Batch() {
	    	super();
	    }
	    
	    public Batch(int batchId, String batchName, String startTime, String endTime, String dayOfWeek) {
	        this.batchId = batchId;
	        this.batchName = batchName;
	        this.startTime = startTime;
	        this.endTime = endTime;
	        this.dayOfWeek = dayOfWeek;
	    }

		public int getBatchId() {
			return batchId;
		}

		public void setBatchId(int batchId) {
			this.batchId = batchId;
		}

		public String getBatchName() {
			return batchName;
		}

		public void setBatchName(String batchName) {
			this.batchName = batchName;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public String getDayOfWeek() {
			return dayOfWeek;
		}

		public void setDayOfWeek(String dayOfWeek) {
			this.dayOfWeek = dayOfWeek;
		}

		@Override
		public String toString() {
			return "Batch [batchId=" + batchId + ", batchName=" + batchName + ", startTime=" + startTime + ", endTime="
					+ endTime + ", dayOfWeek=" + dayOfWeek + "]";
		}
}

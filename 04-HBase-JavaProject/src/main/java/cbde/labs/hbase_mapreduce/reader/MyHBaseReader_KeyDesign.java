package cbde.labs.hbase_mapreduce.reader;

public class MyHBaseReader_KeyDesign extends MyHBaseReader {

	protected String scanStart() {
		return "0";
	}
	
	protected String scanStop() {
		return "a0";
	}
		
}

package cbde.labs.hbase_mapreduce.reader;

public class MyHBaseReader_KeyDesign extends MyHBaseReader {

	protected String scanStart() {
		return "type_3-0-0";
	}
	
	protected String scanStop() {
		return "type_3-1-0";
	}
		
}

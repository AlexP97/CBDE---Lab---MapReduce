package cbde.labs.hbase_mapreduce.writer;

public class MyHBaseWriter_KeyDesign extends MyHBaseWriter {
	int otherKey = 0;

	protected String nextKey() {
		String new_key;
		if(this.data.get("type") == "type_3" && this.data.get("region") == "0")
			new_key = String.valueOf(this.key);
		else
			new_key = "a"+String.valueOf(this.otherKey);
		otherKey++;
		return new_key;
	}
		
}

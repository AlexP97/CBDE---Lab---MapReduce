package cbde.labs.hbase_mapreduce.writer;

public class MyHBaseWriter_KeyDesign extends MyHBaseWriter {

	protected String nextKey() {
		String new_key;
		new_key = this.data.get("type")+"-"+this.data.get("region")+"-"+this.key;
		return new_key;
	}
}

package cbde.labs.hbase_mapreduce.reader;

public class MyHBaseReader_VerticalPartitioning extends MyHBaseReader {

	protected String[] scanFamilies() {
		String[] families = {"fam_1","fam_2"};
		return families;
	}
		
}


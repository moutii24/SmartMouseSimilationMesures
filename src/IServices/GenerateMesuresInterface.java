package IServices;
import java.util.Random;

import Services.*;

public interface GenerateMesuresInterface {
	public Data GenerateDataCond(int aStart, int aEnd, Random aRandom);
	public Data GenerateDataTemp(int aStart, int aEnd, Random aRandom);
	public void saveDataCond(Data cond);
	//public Boolean findID(int ID, String tableName);
}

package com.young.view.happycoin.tool;


import com.young.view.happycoin.R;
import com.young.view.happycoin.HappyCoinApplication;

public class Urls {
	public static String url="http://127.0.0.1:17001";

	public static String ipServer = PrefTool.getStringPerferences(PrefTool.getSharedPreferences("setting", HappyCoinApplication.getInstance()), HappyCoinApplication.getInstance(), Prefs.PRE_SERVER_ADDRESS, Urls.url);

	public static String nameSpaceBusiness = HappyCoinApplication.getInstance().getString(R.string.nameSpaceBusiness);

	public static String nameSpaceUpdate = HappyCoinApplication.getInstance().getString(R.string.nameSpaceUpdate);

	public static String nameSpacePowerCut = HappyCoinApplication.getInstance().getString(R.string.nameSpacePowerCut);

	public static String nameSpaceBusinessquery = HappyCoinApplication.getInstance().getString(R.string.nameSpaceBusinessquery);

	public static String nameSpaceBoxMeterUpload = HappyCoinApplication.getInstance().getString(R.string.nameSpaceBoxMeterUpload);

	public static String nameSpaceBussinesstaskCtrlCreate = HappyCoinApplication.getInstance().getString(R.string.nameSpaceBussinesstaskCtrlCreate);

	public static String nameSpaceBussinesstaskOrderQuery = HappyCoinApplication.getInstance().getString(R.string.nameSpaceBussinesstaskOrderQuery);

	public static String urlBusiness = ipServer + "/md_sgmj_i/services/ITaskManageService?wsdl";

	public static String urlUpdate = ipServer + "/md_sgmj_i/services/ITmnlManageService?wsdl";

	public static String  urlCollect = ipServer + "/md_sgmj_i/services/ITaskManageCollectService?wsdl";

	public static String  urlMeterJY = ipServer + "/md_sgmj_i/services/ITaskMeterExchangeService?wsdl";

	public static String urlBussinesstaskCtrlCreate = ipServer + "/md_sgmj_i/services/ITaskPowerCutService?wsdl";

	public static String urlBussinessOrderQuery = ipServer + "/md_sgmj_i/services/ITaskRegister?wsdl";


}

	var uMIB = 0; // use bullets on Menu Items
	var uMLB = 0; // use bullets on Menu Labels
    var currentFlow = 'Home';
    
	if (uMIB == 1) {
		var mIBOnSrc = "/zeprs/images/menu/bullets/menu_on.gif";
		var mIBOffSrc = "/zeprs/images/menu/bullets/menu_off.gif";
		var mIBW = 13;
		var mIBH = 15;

		mIBOff= new Image(mIBW,mIBH);
		mIBOff.src=mIBOffSrc;
		mIBOn= new Image(mIBW,mIBH);
		mIBOn.src=mIBOnSrc;

		var sMBOnSrc = "/zeprs/images/menu/bullets/sub_header_on.gif";
		var sMBOffSrc = "/zeprs/images/menu/bullets/sub_header_off.gif";
		var sMBW = 13;
		var sMBH = 15;

		sMBOff= new Image(sMBW,sMBH);
		sMBOff.src=sMBOffSrc;
		sMBOn= new Image(sMBW,sMBH);
		sMBOn.src=sMBOnSrc;
	}

	if (uMLB == 1) {
		var mLBOnSrc = "/zeprs/images/menu/bullets/header_on.gif";
		var mLBOffSrc = "/zeprs/images/menu/bullets/header_off.gif";
		var mLBW = 13;
		var mLBH = 15;

		mLBOff= new Image(mLBW,mLBH);
		mLBOff.src=mLBOffSrc;
		mLBOn= new Image(mLBW,mLBH);
		mLBOn.src=mLBOnSrc;
	}

	var sMASrc = "/zeprs/images/menu/bullets/sub_menu.gif";
	var sMAW = 12;
	var sMAH = 16;

	sMA= new Image(sMAW,sMAH);
	sMA.src=sMASrc;

	var ns4 = document.layers;
	var op5 = (navigator.userAgent.indexOf("Opera 5")!=-1) ||(navigator.userAgent.indexOf("Opera/5")!=-1);
	var currentMenu = 0;
	var currentMenuItem = 0;

	var bC = '#999999';

	var timeOn = null;
	var numMenus = 18;
	var numTiers = 2;
	var tier = new Array(numMenus);
	var offColours = new Array(numMenus);
	var onColours = new Array(numMenus);
	var menuActive = new Array(numMenus);
	var imageMenu = new Array(numMenus);
    for(counter = 1; counter <= numMenus; counter++) {
		menuActive[counter] = false;
		imageMenu[counter] = false;
	}
	var myStyle = new Array(numMenus);
	var menuWidths = new Array(numMenus);

	var targetType = 'self';
	var targetFrame = '_self';

// Cascading dHTML menu system v2.0
// James Austin 2003

function openMe(newin) {
        flyout=window.open(newin,"flyout","")
}

function menuOver() {
	clearTimeout(timeOn);
}

function menuOut() {
	timeOn = setTimeout("hideAllMenus()", 500);
}

function showMenuBlank(m_No, eventObj) {
	hideAllMenus();
	if (imageMenu[m_No] == false) {
	    if(!ns4) {
			var menuBox = getStyleObject('labelBox'+m_No);
			menuBox.backgroundColor = onColours[m_No];
	    } else  {
			myStyle[m_No].bgColor=onColours[m_No];
	    }
	}
	menuActive[m_No] = true;
}

function showMenu(m_No, eventObj, align) {

	hideAllMenus();
    if(!ns4) {
		var x = 10;
		var menuTop = 100;
		var menu = getStyleObject('menu'+m_No);
		x = getElementLeft('menuLabel'+m_No);
		menuTop = getElementTop('menuLabel'+m_No);
		if (op5) {
			if (imageMenu[m_No] == true) menuTop = getElementTop('menuLabel'+m_No) + getElementHeight('menuLabel'+m_No);
			else menuTop = getElementTop('menuLabel'+m_No) + 14;
			menu.pixelTop=menuTop;
 			menu.pixelLeft=x;
		} else {
			if (align == 'left') {
				menu.top=(1+menuTop+getElementHeight('menuLabel'+m_No)) +'px';
 				menu.left=x +'px';
			}
			if (align == 'centre') {
				menu.top=(1+menuTop+getElementHeight('menuLabel'+m_No)) +'px';
 				menu.left=((getElementWidth('menuLabel'+m_No)-getElementWidth('menu'+m_No))/2) + x +'px';
			}
			if (align == 'right') {
				menu.top=(1+menuTop+getElementHeight('menuLabel'+m_No)) +'px';
 				menu.left=(x + getElementWidth('menuLabel'+m_No)-getElementWidth('menu'+m_No)) + 1 +'px';
			}
		}
		var menuBox = getStyleObject('labelBox'+m_No);
		menuBox.backgroundColor = onColours[m_No];
    } else  {
		var menu = getStyleObject('menu'+m_No);
		if (imageMenu[m_No]==true) {
			var img = getImage('menuLabel' + m_No);
			y = getImagePageTop(img) + getImagePageHeight(img);
		} else {
			var img = getImage('label' + m_No);
			myStyle[m_No].bgColor=onColours[m_No];
			y = getImagePageTop(img) + 14;
		}
		x = getImagePageLeft(img);
		menu.top = y;
 		menu.left = x-1;
    }
    var menuId = 'menu' + m_No;
	menuActive[m_No] = true;
    if(changeObjectVisibility(menuId, 'visible')) {
		return true;
    } else {
		return false;
    }
}

function showSideMenu(m_No, eventObj) {
	hideAllMenus();
    if(!ns4) {
		var x = 10;
		var menuTop = 100;
		var menu = getStyleObject('menu'+m_No);
		x = getElementLeft('menuLabel'+m_No);
		menuTop = getElementTop('menuLabel'+m_No);
		if (op5) {
			if (imageMenu[m_No] == true) {
				menuTop = getElementTop('menuLabel'+m_No);
				x = getElementLeft('menuLabel'+m_No) + getElementWidth('menuLabel'+m_No);
			} else {
				menuTop = getElementTop('label'+m_No);
				x = getElementLeft('label'+m_No);
			}
			menu.pixelTop=menuTop;
 			menu.pixelLeft=x;
		} else {
			menu.top=menuTop +'px';
 			menu.left=(x + getElementWidth('menuLabel'+m_No)) +'px';
		}
		var menuBox = getStyleObject('labelBox'+m_No);
		menuBox.backgroundColor = onColours[m_No];
    } else  {
		var menu = getStyleObject('menu'+m_No);
		if (imageMenu[m_No]==true) {
			var img = getImage('menuLabel' + m_No);
			x = getImagePageLeft(img) + getImagePageWidth(img);
		} else {
			var img = getImage('label' + m_No);
			myStyle[m_No].bgColor=onColours[m_No];
			x = getImagePageLeft(img) + 2;
		}
		y = getImagePageTop(img);
		menu.top = y;
 		menu.left=x+2;
    }
    var menuId = 'menu' + m_No;
	menuActive[m_No] = true;
    if(changeObjectVisibility(menuId, 'visible')) {
		return true;
    } else {
		return false;
    }
}


function showSubMenu(m_No, eventObj, tier, myAlign) {
   	hideAllMenusTier(tier);
    if(!ns4) {
		var x = 10;
		var menuTop = 100;
		var menu = getStyleObject('menu'+m_No);
		if (myAlign=='right') {
			x = getElementLeft('label'+m_No) + getElementWidth('label'+m_No);;
			menuTop = getElementTop('label'+m_No);
			if(imageMenu[m_No]==1) x = x;
			if (op5) {
				menu.pixelTop=menuTop;
 				menu.pixelLeft= x;
			} else {
				menu.top = menuTop +'px';
 				menu.left = x +'px';
			}
		} else {
			x = getElementLeft('label'+m_No);
			menuTop = getElementTop('label'+m_No);
			if (op5) {
				menu.pixelTop = menuTop ;
 				menu.pixelLeft = (x - menuWidths[m_No] - mIBW);
			} else {
				menu.top = menuTop + 'px';
	 			menu.left = (x - menuWidths[m_No] - mIBW) + 'px';
			}
		}
		var menuBox = getStyleObject('labelBox'+m_No);
		menuBox.backgroundColor = onColours[m_No];
    } else  {
		var img = getImage('label' + m_No);
 		var x = getImagePageLeft(img) + getImagePageWidth(img);
 		var y = getImagePageTop(img);
		var menu = getStyleObject('menu'+m_No);
		if (myAlign=='right') {
			menu.left = x;
 			menu.top = y-2;
		} else {
			menu.left = x - menu.clip.right;
 			menu.top = y-2;
		}
		if (imageMenu[m_No] == false) myStyle[m_No].bgColor=onColours[m_No];
    }
	menuActive[m_No] = true;
    var menuId = 'menu' + m_No;
    if(changeObjectVisibility(menuId, 'visible')) {
		return true;
    } else {
		return false;
    }
}


function hideAllMenus() {
    for(counter = 1; counter <= numMenus; counter++) {
		if (menuActive[counter] == true) {
			changeObjectVisibility('menu' + counter, 'hidden');
			menuActive[counter] = false;
			if (imageMenu[counter] == false) {
				if (ns4) {
					if (myStyle[counter]) myStyle[counter].bgColor=offColours[counter];
				} else {
					var menuBox = getStyleObject('labelBox'+counter);
					menuBox.backgroundColor = offColours[counter];
				}
			}
   		}
   }
}

function hideAllMenusTier(tierNum) {
    for(counter = 1; counter <= numMenus; counter++) {
		if (menuActive[counter] == true) {
			if (tier[counter] > tierNum) {
				changeObjectVisibility('menu' + counter, 'hidden');
				menuActive[counter] = false;
				if (imageMenu[counter] == false) {
					if (ns4) {
						myStyle[counter].bgColor=offColours[counter];
					} else {
						var menuBox = getStyleObject('labelBox'+counter);
						menuBox.backgroundColor = offColours[counter];
					}
				}
			}
    	}
	}
}



function startMenuBar(width, numMenus) {
	currentMenuLabel = 0;
	if (ns4) {
		document.write('<table width="' + width + '" cellpadding="0" cellspacing="0" border="0">');
		if (bC != null)	{
			document.write('<tr><td bgColor="' + bC + '" colspan="' + ((numMenus*2) + 1) + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td></tr>');
		}
		document.write('<tr>');
		if (bC != null)	{
			document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>\n');
		}
	} else {
		document.write('<table width="' + width + '" cellpadding="0" cellspacing="0" border="0"><tr>');
	}
}

function endMenuBar(numMenus) {
	document.write('</tr>');
	if (ns4) {
		if (bC != null)	{
			document.write('<tr><td bgColor="' + bC + '" colspan="' + ((numMenus*2) + 1) + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td></tr>');
		}
	}
	document.write('</table>\n');
}



function startMenu(m_No, width,flow) {
	currentMenu = m_No;
	menuWidths[m_No] = width;
	currentMenuItem = 0;

    if (flow != null)
    {
     currentFlow = flow;
     //alert(currentFlow);
    }

	if (ns4) {
		document.write('<div id="menu' + m_No + '" name="menu' + m_No + '" class="myMenu">');
		document.write('<table ');
		if (width != null) {
		 	document.write('width="' + width + '" ');
		}
		document.write('cellpadding="0" cellspacing="0" border="0">');
		if (bC != null) {
		document.write('<tr><td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>');
		document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>');
		document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>');
		}
		 document.write('</tr>\n');
	} else {
		document.write('<div id="menu' + m_No + '" name="menu' + m_No + '" class="myMenu">');
		document.write('<table ');
		if (width != null) {
		 	document.write('width="' + width + '" ');
		}
		document.write('cellpadding="0" cellspacing="0" border="0">');
	}
}

function endMenu() {
	document.write('</table></div>');
}

function menuSpacer(width, offColour) {
	var tier2 = tier[currentMenu];
	currentMenuItem = currentMenuItem + 1;

	if(ns4) {
		document.write('<tr>');
		if (bC != null) {
			document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>');
		}
		document.write('<td ');
		if (width != null) {
		 	document.write('width="' + (width+3) + '" ');
		} else {
		 	document.write('width="100%" ');
		}
		document.write('valign="middle"><ilayer width="' + (width+3) + '">');
		document.write('<layer ');
		if (width != null) {
		 	document.write('width="' + (width+3) + '" ');
		} else {
		 	document.write('width="100%" ');
		}
		document.write('onmouseover="menuOver();  hideAllMenusTier(' + tier2 + ');" ');
		document.write('onmouseout="menuOut();" ');
		document.write('bgcolor="' + offColour + '">');
		document.write('<hr>');
		document.write('</layer></ilayer></td>');
		if (bC != null) {
			document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>');
		}
		document.write('</tr>');
		if (bC != null) {
			document.write('<tr><td bgColor="' + bC + '" colspan="3"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td></tr>\n');
		}
	} else {
		document.write('<tr><td ');
		document.write('onMouseover="menuOver(); hideAllMenusTier(' + tier2 + ');" ');
		document.write('onMouseout = "menuOut();" ');
		if (width != null) {
			document.write('width="100%" ');
		}
		document.write('bgcolor="' + offColour + '" valign="middle" height="10" ');
		if (bC != null) {
			document.write('style="border: ' + bC + ' 1px solid;"');
		}
		document.write('><div>');
		document.write('<hr>');
		document.write('</div></td></tr>');
	}
}

function getJsessionid(variable) {
  var query = window.location.search.substring(1);
  var path = window.location.href;
  //alert(path +" query: " + query + ":" + query.length);
  //path = path.slice(0-path.length,query.length);
  //var vars = query.split("&");
  //alert(path);
  //var path2 = path.substring(0, path.indexOf("?"))
  //alert("path2: " + path2);
  var vars = path.split(";");
  for (var i=0;i<vars.length;i++) {
   //alert(var);
    var pair = vars[i].split("=");
    //alert(pair);
    if (pair[0] == variable) {
        //alert("pair[1].indexOf(?): " + pair[1].indexOf("?"));
        if  (pair[1].indexOf("?") > 0)
        {
            jsessionid = pair[1].substring(0, pair[1].indexOf("?"))
        }
        else
        {
            jsessionid = pair[1];
        }
      return jsessionid;
    }
  }
  alert('Path Variable ' + variable + ' not found in' + path);
}

jsessionid = getJsessionid("jsessionid");

function menuItem(label, m_No, width, onColour, offColour, url, myAlign) {
    url = url + ";jsessionid=" + jsessionid + "?flow=" + currentFlow;
	if (m_No != null) {
		offColours[m_No] = offColour;
		onColours[m_No] = onColour;
		tier[m_No] = tier[currentMenu] + 1;
	}
	var tier2 = tier[currentMenu];
	currentMenuItem = currentMenuItem + 1;
	mI_No = 'mI' + currentMenu + currentMenuItem;

	if(ns4) {
		document.write('<tr>');
		if (bC != null) {
			document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>');
		}
		document.write('<td ');
		if (width != null) {
		 	document.write('width="' + width + '" ');
		} else {
		 	document.write('width="100%" ');
		}
		document.write('valign="middle">');
		document.write('<ilayer id="menuItemOuter' + mI_No + '" name="menuItemOuter' + mI_No + '">');
		document.write('<layer ');
		if (width != null) {
		 	document.write('width="' + width + '" ');
		} else {
		 	document.write('width="100%" ');
		}
		if (m_No != null) {
			document.write('onmouseover="menuOver(); return !showSubMenu(' + m_No + ', event, ' + tier2 + ', \'' + myAlign + '\');" ');
			document.write('onmouseout="menuOut();" ');
		} else {
			document.write('onmouseover="bgColor=\'' + onColour + '\'; menuOver();  hideAllMenusTier(' + tier2 + ');" ');
			document.write('onmouseout="bgColor=\'' + offColour + '\'; menuOut();" ');
		}
		document.write('id="menuItem' + mI_No + '" name="menuItem' + mI_No + '" ');
		document.write('bgcolor="' + offColour + '">');
		if (m_No == null) document.write('<img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0" align="right">\n');
		document.write('<a class="MenuItemLink" href="' + url +'" target="' + targetFrame + '">\n');
		if (uMIB == 1) {
			if (m_No != null ) document.write('<img src="' + sMBOff.src + '" width="' + sMBW + '" height="' + sMBH + '" border="0" align="left" name="menuBullet' + mI_No + '" id="menuBullet' + mI_No + '">\n');
			else document.write('<img src="' + mIBOff.src + '" width="' + mIBW + '" height="' + mIBH + '" border="0" align="left" name="menuBullet' + mI_No + '" id="menuBullet' + mI_No + '">\n');
		}
		if (m_No != null) document.write('<img src="' + sMA.src + '" width="' + sMAW + '" height="' + sMAH + '" border="0" align="right">');
		document.write(label + '</a>');
		document.write('</layer></ilayer>');
		if (m_No != null) document.write('<img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0" align="' + myAlign + '" id="label' + m_No + '" name="label' + m_No + '">');
		document.write('</td>');
		if (bC != null) {
			document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>');
		}
		document.write('</tr>');
		if (bC != null) {
			document.write('<tr><td bgColor="' + bC + '" colspan="3"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td></tr>\n');
		}
		if (m_No != null) myStyle[m_No] = eval('document.menu' + currentMenu + '.document.menuItemOuter' + mI_No + '.document.menuItem' + mI_No);
	} else {
		document.write('<tr><td ');
		if (width != null) {
		 	document.write('width="100%" ');
		}
		document.write('bgcolor="' + offColour + '" valign="middle" height="12" ');
		if (bC != null) {
			if (currentMenuItem == 1) {
				document.write('style="border: ' + bC + ' 1px solid;" ');
			} else {
				document.write('style="border-left: ' + bC + ' 1px solid;border-right: ' + bC + ' 1px solid;border-bottom: ' + bC + ' 1px solid;" ');
			}
		}
		document.write('id="labelBox' + m_No + '" name="labelBox' + m_No + '" ');
		if (m_No != null) {
			document.write('onmouseover="');
			if (uMIB == 1) {
				document.write('menuBullet' + mI_No + '.src=sMBOn.src; ');
			}
			document.write('menuOver(); return !showSubMenu(' + m_No + ', event, ' + tier2 + ', \'' + myAlign + '\');"  ');
			document.write('onmouseout="menuOut(); ');
		} else {
			document.write('onMouseover="this.style.backgroundColor = \'' + onColour + '\'; ');
			if (uMIB == 1) {
				document.write('menuBullet' + mI_No + '.src=mIBOn.src; ');
			}
			document.write('menuOver(); hideAllMenusTier(' + tier2 + ');" ');
			document.write('onMouseout = "this.style.backgroundColor = \'' + offColour + '\'; menuOut(); ');
		}
		if (uMIB == 1) {
			if (m_No != null) document.write('menuBullet' + mI_No + '.src=sMBOff.src;" ');
			else document.write('menuBullet' + mI_No + '.src=mIBOff.src;" ');
		} else {
			document.write('"');
		}
		if (targetType=='self') document.write('onClick="document.location.href=\'' + url + '\';" ');
		if (targetType=='new') document.write('onClick="openMe(\'' + url + '\'); return false;" ');
		if (targetType=='frame') document.write('onClick="parent.' + targetFrame + '.document.location.href=\'' + url + '\';" ');
		if (targetType=='iframe') document.write('onClick="' + targetFrame + '.location.href=\'' + url + '\';" ');
		document.write('<div id="menuItem' + mI_No + '" name="menuItem' + mI_No + '"');
		document.write('><a class="MenuItemLink" href="' + url + '" target="' + targetFrame + '">');
		if (uMIB == 1) {
			if (m_No != null) document.write('<img src="' + sMBOff.src + '" width="' + sMBW + '" height="' + sMBH + '" border="0" align="left" name="menuBullet' + mI_No + '" id="menuBullet' + mI_No + '">');
			else document.write('<img src="' + mIBOff.src + '" width="' + mIBW + '" height="' + mIBH + '" border="0" align="left" name="menuBullet' + mI_No + '" id="menuBullet' + mI_No + '">');
		}
		if(m_No != null) {
			if(myAlign=='right') {
				document.write('<img src="' + sMA.src + '" width="' + sMAW + '" height="' + sMAH + '" border="0" align="right" id="label' + m_No + '" name="label' + m_No + '">');
			} else {
				document.write('<img src="' + sMA.src + '" width="' + sMAW + '" height="' + sMAH + '" border="0" align="right">');
				document.write('<img src="/zeprs/images/menu/shim.gif" width="1" align="top" height="1" border="0"  id="label' + m_No + '" name="label' + m_No + '">');
			}
		} else {
			document.write('<img src="/zeprs/images/menu/shim.gif" width="1" align="top" height="1" border="0">');
		}
		document.write('&nbsp;' + label);
		document.write('</a></div></td></tr>');
	}
}



function getQueryVariable(variable) {
  var query = window.location.search.substring(1);
  var vars = query.split("&");
  for (var i=0;i<vars.length;i++) {
    var pair = vars[i].split("=");
    if (pair[0] == variable) {
      return pair[1];
    }
  }
  //alert('Query Variable ' + variable + ' not found in' + query);
  return "Home";
}
	function menuLabel(label, m_No, width, onColour, offColour, url, align, currentFlow) {
		tier[m_No] = 0;
		offColours[m_No] = offColour;
		onColours[m_No] = onColour;
		currentMenuLabel = currentMenuLabel + 1;
        url = url + ";jsessionid=" + jsessionid + "?flow=" + currentFlow;
		if(ns4) {
			document.write('<td width="' + width + '" valign="middle" height="2" align="left"><ilayer id="menuLabelOuter' + m_No + '" name="menuLabelOuter' + m_No + '"><layer width="' + width + '" onmouseover="menuOver(); ');
			if (align != null) document.write('return !showMenu(' + m_No + ', event, \'' + align + '\'); ');
			else document.write('return !showMenuBlank(' + m_No + ', event, \'' + align + '\'); ');

			document.write('return false;" onmouseout="menuOut();" id="menuLabel' + m_No + '" name="menuLabel' + m_No + '" bgcolor="' + offColour + '"><a class="LabelMenuItemLink" href="' + url + '" target="' + targetFrame + '">');
			if (uMLB == 1) {
				document.write('<img src="' + mLBOff.src + '" width="' + mLBW + '" height="' + mLBH + '" border="0" align="left" name="menuBullet' + m_No + '" id="menuBullet' + m_No + '">');
			}
			document.write(label + '</a></layer></ilayer><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0" align="left" id="label' + m_No + '" name="label' + m_No + '"></td>');
			if (bC != null) {
				document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>\n');
			}
			myStyle[m_No] = eval('document.menuLabelOuter' + m_No + '.document.menuLabel' + m_No);
		} else {
        //alert("Flow: "+getQueryVariable('flow').replace('%20',' ').replace('%20',' ')+"\n Label: " + label);
		if (getQueryVariable('flow').replace('%20',' ').replace('%20',' ')==label)
            {
			offColour="#bbbbbb";
			}
			document.write('<td width="'+width + '" bgcolor="' + offColour + '" valign="middle" height="12" ');
			if (bC != null) {
				if (currentMenuLabel == 1) {
					document.write('style="border: ' + bC + ' 1px solid;" ');
				} else {
					document.write('style="border-top: ' + bC + ' 1px solid; border-bottom: ' + bC + ' 1px solid; border-right: ' + bC + ' 1px solid;" ');
				}
			}

			document.write('id="labelBox' + m_No + '" name="labelBox' + m_No + '" align="left"><div id="menuLabel' + m_No + '" name="menuLabel' + m_No + '" onmouseover="');
			if (uMLB == 1) {
				document.write('label' + m_No + '.src=mLBOn.src; ');
			}
			if (align != null) document.write('menuOver(); return !showMenu(' + m_No + ', event,  \'' + align + '\');"  onmouseout="');
			else document.write('menuOver(); return !showMenuBlank(' + m_No + ', event,  \'' + align + '\');"  onmouseout="');
			if (uMLB == 1) {
				document.write('label' + m_No + '.src=mLBOff.src; ');
			}
			document.write('menuOut();" ');
			if (targetType=='self') document.write('onClick="document.location.href=\'' + url + '\';" ');
			if (targetType=='new') document.write('onClick="openMe(\'' + url + '\'); return false;" ');
			if (targetType=='frame') document.write('onClick="parent.' + targetFrame + '.document.location.href=\'' + url + '\';" ');
			if (targetType=='iframe') document.write('onClick="' + targetFrame + '.location.href=\'' + url + '\';" ');

            if (getQueryVariable('flow').replace('%20',' ')==label)
            {
			document.write('><a class="LabelMenuItemLink" href="' + url + '" target="' + targetFrame + '">');
			}
			else
			{
			document.write('><a class="LabelMenuItemLink" href="' + url + '" target="' + targetFrame + '">');
			//alert("Flow: "+getQueryVariable('flow')+" Label: " + label);
			}

			if (uMLB == 1) {
				document.write('<img src="' + mLBOff.src + '" width="' + mLBW + '" height="' + mLBH + '" border="0" align="left" id="label' + m_No + '" name="label' + m_No + '">');
			} else {
				if (op5) {
					document.write('<img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0" align="left" id="label' + m_No + '" name="label' + m_No + '">');
				}
				document.write('<img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0" align="left" id="label' + m_No + '" name="label' + m_No + '">');
			}
			document.write('&nbsp;' + label + '</a></div></td>\n');
		}
	}


	function startSideMenuBar(width) {
		currentMenuLabel = 0;
		if (ns4) {
			document.write('<table width="' + width + '" cellpadding="0" cellspacing="0" border="0">');
			if (bC != null) {
				document.write('<tr><td bgColor="' + bC + '" colspan="3"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td></tr>\n');
			}
		}
		else document.write('<table width="' + width + '" cellpadding="0" cellspacing="0" border="0">\n');
	}

	function endSideMenuBar() {
		document.write('</table>\n');
	}

	function sideMenuLabel(label, m_No, width, onColour, offColour, url, align) {
		tier[m_No] = 0;
		offColours[m_No] = offColour;
		onColours[m_No] = onColour;
		currentMenuLabel = currentMenuLabel + 1;

		if(ns4) {
			document.write('<tr>');
			if (bC != null) {
				document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>');
			}
			document.write('<td width="' + width + '" valign="middle" height="2"><ilayer id="menuLabelOuter' + m_No + '" name="menuLabelOuter' + m_No + '"><layer width="' + width + '" onmouseover="menuOver(); ');
			if (align != null) document.write('return !showSideMenu(' + m_No + ', event);" ');
			else document.write('return !showMenuBlank(' + m_No + ', event);" ');
			document.write('onmouseout="menuOut();" id="menuLabel' + m_No + '" name="menuLabel' + m_No + '" bgcolor="' + offColour + '"><a class="MenuItemLink" href="' + url + '" target="' + targetFrame + '">\n');
			if (uMLB == 1) {
				document.write('<img src="' + mLBOff.src + '" width="' + mLBW + '" height="' + mLBH + '" border="0" align="left" name="menuBullet' + m_No + '" id="menuBullet' + m_No + '">\n');
			}
			document.write('' + label + '</a></layer></ilayer><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0" align="right" id="label' + m_No + '" name="label' + m_No + '"></td>');
			if (bC != null) {
				document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>');
			}
			document.write('</tr>');
			if (bC != null) {
				document.write('<tr><td bgColor="' + bC + '" colspan="3"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td></tr>\n');
			}
			myStyle[m_No] = eval('document.menuLabelOuter' + m_No + '.document.menuLabel' + m_No);
		} else {
			document.write('<tr><td width="' + width + '" align="left" bgcolor="' + offColour + '" valign="middle" height="12" ');
			if (bC != null) {
				if (currentMenuLabel == 1) {
					document.write('style="border: ' + bC + ' 1px solid;" ');
				} else {
					document.write('style="border-left: ' + bC + ' 1px solid; border-bottom: ' + bC + ' 1px solid; border-right: ' + bC + ' 1px solid;" ');
				}
			}
			document.write('id="labelBox' + m_No + '" name="labelBox' + m_No + '"><div id="menuLabel' + m_No + '" name="menuLabel' + m_No + '" onmouseover="');
			if (uMLB == 1) {
				document.write('menuBullet' + m_No + '.src=mLBOn.src; ');
			}
			if (align != null) document.write('menuOver(); return !showSideMenu(' + m_No + ', event);"  onmouseout="');
			else document.write('menuOver(); return !showMenuBlank(' + m_No + ', event);"  onmouseout="');
			if (uMLB == 1) {
				document.write('menuBullet' + m_No + '.src=mLBOff.src; ');
			}
			document.write('menuOut();" ');
			if (targetType=='self') document.write('onClick="document.location.href=\'' + url + '\';" ');
			if (targetType=='new') document.write('onClick="openMe(\'' + url + '\'); return false;" ');
			if (targetType=='frame') document.write('onClick="parent.' + targetFrame + '.document.location.href=\'' + url + '\';" ');
			if (targetType=='iframe') document.write('onClick="' + targetFrame + '.location.href=\'' + url + '\';" ');
			document.write('><a class="MenuItemLink" href="' + url + '" target="' + targetFrame + '">');
			if (uMLB == 1) {
				document.write('<img src="' + mLBOff.src + '" width="' + mLBW + '" height="' + mLBH + '" border="0" align="top" name="menuBullet' + m_No + '" id="menuBullet' + m_No + '">');
			}
			document.write('&nbsp;' + label + '<img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0" align="right" id="label' + m_No + '" name="label' + m_No + '"></a></div></td></tr>\n');
		}
	}

	function Img_menuItem(m_No, onImage, offImage, url, myAlign) {
	if (m_No != null) {
		imageMenu[m_No] = true;
		tier[m_No] = tier[currentMenu] + 1;
	}
	var tier2 = tier[currentMenu];
	currentMenuItem = currentMenuItem + 1;

	mI_No = 'mI' + currentMenu + currentMenuItem;
	if (m_No != null) {
		document.write('<script language="javascript">on' + m_No + '= new Image();');
		document.write('on' + m_No + '.src="' + onImage + '";');
		document.write('off' + m_No + '= new Image();');
		document.write('off' + m_No + '.src="' + offImage + '";</script>');
	} else {
		document.write('<script language="javascript">');
		document.write('on' + mI_No + '= new Image();');
		document.write('on' + mI_No + '.src="' + onImage + '";');
		document.write('off' + mI_No + '= new Image();');
		document.write('off' + mI_No + '.src="' + offImage + '";');
		document.write('</script>');
	}
	document.write('<tr>');

	if (bC != null && ns4) {
		document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>');
	}

	document.write('<td');
	if (bC != null && !ns4) {
		if (currentMenuItem == 1) {
			document.write(' style="border: ' + bC + ' 1px solid;" ');
		} else {
			document.write(' style="border-left: ' + bC + ' 1px solid;border-right: ' + bC + ' 1px solid;border-bottom: ' + bC + ' 1px solid;" ');
		}
	}
	document.write('>');
	if (m_No != null) {
		document.write('<a href="' + url + '" target="' + targetFrame + '" onmouseover="menuOver(); label' + m_No + '.src = on' + m_No + '.src;  return !showSubMenu(' + m_No + ', event, ' + tier2 + ', \'' + myAlign + '\');"  onmouseout="menuOut(); label' + m_No + '.src = off' + m_No + '.src;">');
		document.write('<img src="' + offImage + '" border="0" id="label' + m_No + '" name="label' + m_No + '"></a></td>');
	} else {
		document.write('<a href="' + url + '" target="' + targetFrame + '" onmouseover="menuItem' + mI_No + '.src = on' + mI_No + '.src; menuOver(); hideAllMenusTier(' + tier2 + ');" onmouseout="menuItem' + mI_No + '.src = off' + mI_No + '.src; menuOut();">');
		document.write('<img src="' + offImage + '" border="0" id="menuItem' + mI_No + '" name="menuItem' + mI_No + '"></a></td>');
	}

	if (bC != null && ns4) {
		document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>');
	}
	document.write('</tr>');
	if (bC != null && ns4) {
		document.write('<tr><td bgColor="' + bC + '" colspan="3"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td></tr>\n');
	}
}


function Img_menuLabel(m_No, onImage, offImage, url, align) {
	tier[m_No] = 0;
	imageMenu[m_No] = true;
	document.write('<script language="javascript">on' + m_No + '= new Image();');
	document.write('on' + m_No + '.src="' + onImage + '";');
	document.write('off' + m_No + '= new Image();');
	document.write('off' + m_No + '.src="' + offImage + '";</script>');

	document.write('<td');
	if (bC != null && !ns4) {
		document.write(' style="border: ' + bC + ' 1px solid;"');
	}
	document.write('>');
	document.write('<a href="' + url + '" target="' + targetFrame + '" onmouseover="menuLabel' + m_No + '.src = on' + m_No + '.src; menuOver(); ');
	if (align != null) document.write('return !showMenu(' + m_No + ', event,  \'' + align + '\');" ');
	else document.write('return !showMenuBlank(' + m_No + ', event,  \'' + align + '\');" ');
	document.write('onmouseout="menuLabel' + m_No + '.src = off' + m_No + '.src; menuOut();">');
	document.write('<img src="' + offImage + '" border="0" id="menuLabel' + m_No + '" name="menuLabel' + m_No + '"></a></td>');

	if (bC != null && ns4) {
		document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>\n');
	}
}

function Img_sideMenuLabel(m_No, onImage, offImage, url, align) {
	tier[m_No] = 0;
	imageMenu[m_No] = true;

	document.write('<script language="javascript">on' + m_No + '= new Image();');
	document.write('on' + m_No + '.src="' + onImage + '";');
	document.write('off' + m_No + '= new Image();');
	document.write('off' + m_No + '.src="' + offImage + '";</script>');

	document.write('<tr>');

	if (bC != null && ns4) {
		document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>');
	}

	document.write('<td');
	if (bC != null && !ns4) {
		document.write(' style="border: ' + bC + ' 1px solid;"');
	}
	document.write('>');
	document.write('<a href="' + url + '" target="' + targetFrame + '" onmouseover="menuLabel' + m_No + '.src = on' + m_No + '.src; menuOver(); ');
	if (align != null) document.write('return !showSideMenu(' + m_No + ', event);" ');
	else document.write('return !showMenuBlank(' + m_No + ', event);" ');
	document.write('onmouseout="menuLabel' + m_No + '.src = off' + m_No + '.src; menuOut();">');
	document.write('<img src="' + offImage + '" border="0" id="menuLabel' + m_No + '" name="menuLabel' + m_No + '"></a></td>');

	if (bC != null && ns4) {
		document.write('<td bgColor="' + bC + '"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td>');
	}

	document.write('</tr>');

	if (bC != null && ns4) {
		document.write('<tr><td bgColor="' + bC + '" colspan="3"><img src="/zeprs/images/menu/shim.gif" width="1" height="1" border="0"></td></tr>\n');
	}

}



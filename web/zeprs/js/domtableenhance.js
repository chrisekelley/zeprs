function domTableEnhance()
{
	if(document.getElementById && document.getElementsByTagName)
	{
		var tableClass='enhancedtable';
		var colourClass='enhancedtablecolouredrow';
		var hoverClass='enhancedtablerowhover';
		var alltables,bodies,i,j,k,addclass,trs,c;
		alltables=document.getElementsByTagName('table');
		for (i=0;i<alltables.length;i++)
		{
			if(alltables[i].className.match(tableClass))
			{
				bodies=alltables[i].getElementsByTagName('tbody');
				for (j=0;j<bodies.length;j++)
				{
					trs=bodies[j].getElementsByTagName('tr')
					for (k=0;k<trs.length;k++)
					{
						if(trs[k].getElementsByTagName('td').length>0)
						{
							addClass=k%2==0?' '+colourClass:'';
							trs[k].className=trs[k].className+addClass;
							trs[k].c=hoverClass;
							trs[k].onmouseover=function()
							{
								this.className=this.className+' '+this.c;
							}
							trs[k].onmouseout=function()
							{
								this.className=this.className.replace(''+this.c,'');
							}
						}
					}
				}
			}
		}
	}
}
window.onload=domTableEnhance; 
/*
    Copyright 2000 Roberto H. Marquez 
 
    This file is part of onebeartoe API for Java.

    onebeartoe API for Java is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    onebeartoe API for Java is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with onebeartoe API for Java.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.onebeartoe.application.ui.swing;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class TabbedPane extends JTabbedPane 
{

	static final long serialVersionUID = 3L;

//	private int tabCount;
	
	public TabbedPane(JComponent ... tabs) 
	{
		super();
		int t = 1;
		for(JComponent tab : tabs) 
		{
			addTab("tab " + t, null, tab, "Tool tip for tab " + t);
			t++;
		}
//		tabCount = t;
	}
	
	public void setTabTitles(String [] titles) 
	{
		int count = getTabCount();
//		setTabCount(titles.length);
		for(int i=0; i<titles.length && i<count; i++) 
		{
			setTitleAt(i, titles[i]);
		}
	}

//	public int getTabCount() 
//	{
//		return tabCount;
//	}
//
//	public void setTabCount(int tabCount) 
//	{
//		this.tabCount = tabCount;
//	}
	
}


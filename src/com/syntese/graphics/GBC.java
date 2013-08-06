package com.syntese.graphics;

 /*
 GBC - A convenience class to tame the GridBagLayout

  4. Copyright (C) 2002 Cay S. Horstmann (http://horstmann.com)
  5.
  6. This program is free software; you can redistribute it and/or modify
  7. it under the terms of the GNU General Public License as published by
  8. the Free Software Foundation; either version 2 of the License, or
  9. (at your option) any later version.
 10.
 11. This program is distributed in the hope that it will be useful,
 12. but WITHOUT ANY WARRANTY; without even the implied warranty of
 13. MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 14. GNU General Public License for more details.
 15.
 16. You should have received a copy of the GNU General Public License
 17. along with this program; if not, write to the Free Software
 18. Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 19. */
 
  import java.awt.*;
 
  /**
     This class simplifies the use of the GridBagConstraints
     class.
  */
  public class GBC extends GridBagConstraints
  {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
        Constructs a GBC with a given gridx and gridy position and
        all other grid bag constraint values set to the default.
 32.       @param gridx the gridx position
 33.       @param gridy the gridy position
 34.    */
     public GBC(int gridx, int gridy)
     {
        this.gridx = gridx;
        this.gridy = gridy;
     }
 
     /**
 42.       Constructs a GBC with given gridx, gridy, gridwidth, gridheight
 43.       and all other grid bag constraint values set to the default.
 44.       @param gridx the gridx position
 45.       @param gridy the gridy position
 46.       @param gridwidth the cell span in x-direction
 47.       @param gridheight the cell span in y-direction
 48.    */
     public GBC(int gridx, int gridy, int gridwidth, int gridheight)
     {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
     }
 
     /**
 58.       Sets the anchor.
 59.       @param anchor the anchor value
 60.       @return this object for further modification
 61.    */
     public GBC setAnchor(int anchor)
     {
        this.anchor = anchor;
        return this;
     }
 
     /**
 69.       Sets the fill direction.
 70.       @param fill the fill direction
 71.       @return this object for further modification
 72.    */
     public GBC setFill(int fill)
     {
        this.fill = fill;
        return this;
     }
 
     /**
 80.       Sets the cell weights.
 81.       @param weightx the cell weight in x-direction
 82.       @param weighty the cell weight in y-direction
 83.       @return this object for further modification
 84.    */
     public GBC setWeight(double weightx, double weighty)
     {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
     }
 
     /**
 93.       Sets the insets of this cell.
 94.       @param distance the spacing to use in all directions
 95.       @return this object for further modification
 96.    */
     public GBC setInsets(int distance)
     {
        this.insets = new Insets(distance, distance, distance, distance);
       return this;
    }

    /**
       Sets the insets of this cell.
105.       @param top the spacing to use on top
106.       @param left the spacing to use to the left
107.       @param bottom the spacing to use on the bottom
108.       @param right the spacing to use to the right
109.       @return this object for further modification
110.    */
    public GBC setInsets(int top, int left, int bottom, int right)
    {
       this.insets = new Insets(top, left, bottom, right);
       return this;
    }

    /**
       Sets the internal padding
119.       @param ipadx the internal padding in x-direction
120.       @param ipady the internal padding in y-direction
121.       @return this object for further modification
122.    */
    public GBC setIpad(int ipadx, int ipady)
    {
       this.ipadx = ipadx;
       this.ipady = ipady;
       return this;
    }
 }

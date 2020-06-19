package view;

public enum nameEvent
{
	// create the list 
	// Shape
    CHOOSE_SHAPE 
    {
        @Override
        public String toString() 
        {
            return "CHOOSE SHAPE";
        }
    },
    // primary color
    CHOOSE_PRIMARY_COLOR 
    {
        @Override
        public String toString() 
        {
            return "CHOOSE PRIMARY COLOR";
        }
    },
    // secondary color
    CHOOSE_SECONDARY_COLOR
    {
        @Override
        public String toString()
        {
            return "CHOOSE SECONDARY COLOR";
        }
    },
    // shading type
    CHOOSE_SHADING_TYPE 
    {
        @Override
        public String toString() 
        {
            return "CHOOSE SHADING TYPE";
        }
    },
    // start and end point
    CHOOSE_START_POINT_endValuePoint_MODE 
    {
        @Override
        public String toString() 
        {
            return "CHOOSE START /END POINT MODE";
        }
    },
   
    // clear
    CLEAR 
    {
        @Override
        public String toString()
        {
            return "CLEAR ALL";
        }
    }

}

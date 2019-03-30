/*------------------------------------------------------------------
 * FiLE: Course.java
 * VERSION: 03/Feb/2019
 * PROGRAMMERS:
 * Edgar Elias - 7515927
 * Daniel Baker - 6461982
 * Irina Alexandru - 7925423
 * Samuel Lyra - 7763881
 *
 * DESCRIPTION:
 *
 * -----------------------------------------------------------------*/

package phoenix.assigment1;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable
{
    /**************************************************************************************************/
    /*                                      PRIVATE ATTRIBUTES                                        */
    /**************************************************************************************************/
    private static final Course instance = new Course(); //If the class is meant to be a Singleton
    private String courseName;                           //The Name of the course
    private String colourType;                          //The Colour of the course
    private ArrayList<Assignment> assignments = new ArrayList<Assignment>(); //The list of assignments in this course

    /**************************************************************************************************/
    /*                                       PUBLIC METHODS                                           */
    /**************************************************************************************************/

    /**************************************** Method Header Comment ************************************
     Name       : Course -- CONSTRUCTOR
     Purpose    : This default constructor does not need any code at the moment.
     Inputs     : None.
     Outputs    : None.
     Returns	: Nothing.
     ****************************************************************************************************/
    public Course()
    {
        //default constructor
    }

    /**************************************** Method Header Comment ************************************
     Name       : GetInstance -- Method
     Purpose    : Returns the instance of the class if it is meant to be a Singleton.
     Inputs     : None.
     Outputs    : None.
     Returns	: Returns the instance of the class.
     ****************************************************************************************************/
    public static Course GetInstance()
    {
        return instance;
    }

    /**************************************** Method Header Comment ************************************
     Name       : SetCourseName -- Setter
     Purpose    : To set the name of a course.
     Inputs     : None.
     Outputs    : None.
     Returns	: True on success, otherwise it will return false.
     ****************************************************************************************************/
    public boolean SetCourseName(String newCourse)
    {
        boolean valid = false;
        if(newCourse.trim() != "" || newCourse.trim() != null)
        {
            this.courseName = newCourse;
            valid = true;
        }
        return valid;
    }

    /**************************************** Method Header Comment ************************************
     Name       : SetColourType -- Setter
     Purpose    : To set the colour of the course.
     Inputs     : None.
     Outputs    : None.
     Returns	: True on success, otherwise it will return false.
     ****************************************************************************************************/
    public  boolean SetColourType(String newColour)
    {
        boolean valid = false;
        if(newColour.trim() != "" || newColour.trim() != null)
        {
            this.colourType = newColour;
            valid = true;
        }
        return valid;
    }

    /**************************************** Method Header Comment ************************************
     Name       : SetAssignment -- Setter
     Purpose    : To add an assignment to the list of the courses' assignments.
     Inputs     : None.
     Outputs    : None.
     Returns	: True on success, otherwise it will return false.
     ****************************************************************************************************/
    public boolean SetAssignment(Assignment assignment)
    {
        boolean valid = false;
        if(assignment != null)
        {
            this.assignments.add(assignment);
            valid = true;
        }
        return valid;
    }

    /**************************************** Method Header Comment ************************************
     Name       : GetCourseName -- Getter
     Purpose    : To get the desired attribute.
     Inputs     : None.
     Outputs    : None.
     Returns	: The data member.
     ****************************************************************************************************/
    public String GetCourseName()
    {
        return courseName;
    }

    /**************************************** Method Header Comment ************************************
     Name       : GetColourType -- Getter
     Purpose    : To get the desired attribute.
     Inputs     : None.
     Outputs    : None.
     Returns	: The data member.
     ****************************************************************************************************/
    public  String GetColourType()
    {
        return  colourType;
    }

    /**************************************** Method Header Comment ************************************
     Name       : GetAssignments -- Getter
     Purpose    : To get the desired attribute.
     Inputs     : None.
     Outputs    : None.
     Returns	: The data member.
     ****************************************************************************************************/
    public ArrayList<Assignment> GetAssignments()
    {
        return assignments;
    }

    /**************************************** Method Header Comment ************************************
     Name       : GetAnAssignment -- Getter
     Purpose    : To get an specific assignment from the list of assignments.
     Inputs     : None.
     Outputs    : None.
     Returns	: An assignment.
     ****************************************************************************************************/
    public Assignment GetAnAssignment(int assignmentIndex)
    {
        return this.assignments.get(assignmentIndex);
    }

}

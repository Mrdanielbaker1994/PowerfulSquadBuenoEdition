/*------------------------------------------------------------------
 * FiLE: Assignment.java
 * VERSION: 03/Feb/2019
 * PROGRAMMERS:
 * Edgar Elias - 7515927
 * Daniel Baker - 6461982
 * Irina Alexandru - 7925423
 * Samuel Lyra - 7763881
 *
 * DESCRIPTION: The purpose of this class is to model and attributes
 *              of an assignment. An assignment is a base class from
 *              the Course class. Moreover, an assignment in this
 *              application can have a title for the assignment and a
 *              due date.
 *
 * -----------------------------------------------------------------*/

package phoenix.assigment1;
import java.io.Serializable;

public class Assignment implements Serializable
{

    /**************************************************************************************************/
    /*                                      PRIVATE ATTRIBUTES                                        */
    /**************************************************************************************************/
    private String title;
    private String dueDate;

    /**************************************************************************************************/
    /*                                       PUBLIC METHODS                                           */
    /**************************************************************************************************/

    /**************************************** Method Header Comment ************************************
     Name       : Assignment -- CONSTRUCTOR
     Purpose    : This default constructor does not need any code at the moment.
     Inputs     : None.
     Outputs    : None.
     Returns	: Nothing.
     ****************************************************************************************************/
    public Assignment()
    {
        //nothing
    }

    /**************************************** Method Header Comment ************************************
     Name       : GetAssignmentDetails -- Getter
     Purpose    : To get both title and due date in a formatted string.
     Inputs     : None.
     Outputs    : None.
     Returns	: A formatted string.
     ****************************************************************************************************/
    public String GetAssignmentDetails()
    {
        String strBuilder = this.title.toUpperCase() + "\n" + this.dueDate;
        return  strBuilder;
    }

    /**************************************** Method Header Comment ************************************
     Name       : getTitle -- Getter
     Purpose    : To get the desired attribute.
     Inputs     : None.
     Outputs    : None.
     Returns	: The data member.
     ****************************************************************************************************/
    public String getTitle() {
        return title;
    }


    /**************************************** Method Header Comment ************************************
     Name       : getDueDate -- Getter
     Purpose    : To get the desired attribute.
     Inputs     : None.
     Outputs    : None.
     Returns	: The data member.
     ****************************************************************************************************/
    public String getDueDate() {
        return dueDate;
    }


    /**************************************** Method Header Comment ************************************
     Name       : setTitle -- Setter
     Purpose    : To set the name of an assignment.
     Inputs     : None.
     Outputs    : None.
     Returns	: True on success, otherwise it will return false.
     ****************************************************************************************************/
    public void setTitle(String title) {
        this.title = title;
    }

    /**************************************** Method Header Comment ************************************
     Name       : setDueDate -- Setter
     Purpose    : To set the due date of an assignment.
     Inputs     : None.
     Outputs    : None.
     Returns	: True on success, otherwise it will return false.
     ****************************************************************************************************/
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }


}

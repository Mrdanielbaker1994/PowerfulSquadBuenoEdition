/*------------------------------------------------------------------
* FiLE: AddAssignmentActivity.java
* VERSION: 03/Feb/2019
* PROGRAMMERS:
* Edgar Elias - 7515927
* Daniel Baker - 6461982
* Irina Alexandru - 7925423
* Samuel Lyra - 7763881
*
* DESCRIPTION: The purpose of this file is to handle the activity
*              events when the a new assignments is going to be added
*              to a specific course.
* -----------------------------------------------------------------*/

package phoenix.assigment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;

public class AddAssignmentActivity extends AppCompatActivity
{
    /*Globals*/
    ArrayList<Course> myCourses;
    Course course;
    Assignment assignment = new Assignment();
    int retrieveCourseIdx;


    /*----------------------------------------------------------
     * Function: onCreate()
     * Parameters: Bundle savedInstanceState
     * Description: The purpose of this method is to process
     *              the Intents and initiate a event listener
     *              in the event that the user selects an
     *              specific event.
     * Returns: Void.
     * --------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        /*Process the course that will get updated with new assignments*/
        processCourseIntent();

        /*Create an event listener when a date is selected on the calendar widget*/
        CalendarView myCalendar = (CalendarView) findViewById(R.id.calendarViewID);
        myCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
            {
                /*Build a string upon the selected date*/
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                assignment.setDueDate(date);
            }
        }); //end of event listener
    }

    /*----------------------------------------------------------
     * Function: cancelAssgnBtnClick()
     * Parameters: View view
     * Description: The purpose of this method is to process the
     *              event when the user wants to cancel the action
     *              of this activity. In this case, we will
     *              assign to the intent the same values that the
     *              previous intent sent and send them back. Then,
     *              we will transfer to the previous activity.
     * Returns: Void.
     * --------------------------------------------------------*/
    public void cancelAssgnBtnClick(View view)
    {
        try
        {
            /*Process the Intent!*/
            Bundle bundle = new Bundle();
            bundle.putSerializable("courseList", myCourses);

            /*Fill the Intent object and get outta here!*/
            Intent i = new Intent(this, VisualizeCourseActivity.class);
            i.putExtras(bundle); //The serialized Course() object will be assigned to the intent
            i.putExtra("courseToInspect", retrieveCourseIdx); //The index of the modified course
            startActivity(i); //Go back to the VisualizeCourseActivity
        }
        catch (Exception e)
        {
            Log.d("AddAssignmentActivity", "Something went wrong when the user pressed the CANCEL button");
        }
    }

    /*----------------------------------------------------------
     * Function: processCourseIntent()
     * Parameters: None
     * Description: The purpose of this method is to process the
     *              intent from the previous Activity. Moreover,
     *              this method will get the ArrayList of all the
     *              courses and the Index in the list of the Course
     *              we want to retrieve and assignments to it.
     * Returns: Void.
     * --------------------------------------------------------*/
    protected void processCourseIntent()
    {
        Intent retrieveCourses = this.getIntent(); //get the intent
        try
        {
            /*Get the object from the intent*/
            Bundle getBundledObjects = retrieveCourses.getExtras();
            if(getBundledObjects != null)
            {
                /*The following lines of code will get the list of all the current courses and
                 * the course index in the list that we want to manipulate*/
                myCourses = (ArrayList<Course>) getBundledObjects.getSerializable("courseList"); //Get the list of current courses
                retrieveCourseIdx = retrieveCourses.getIntExtra("courseToInspect", -1);
                if(retrieveCourseIdx != -1) //error checking
                {
                    course = myCourses.get(retrieveCourseIdx); //Get the selected course
                }
            }
        }
        catch (Exception e)
        {
            Log.d("AddAssignmentActivity", "Something went wrong when processing the Intent from the previous activity");
        }
    }

    /*----------------------------------------------------------
     * Function: saveAssignmentBBClick()
     * Parameters: View view
     * Description: The purpose of this method is to save the
     *              all the changes that were made to this course.
     *              Then, we will transfer to the previous activity.
     * Returns: Void.
     * --------------------------------------------------------*/
    public void saveAssignmentBBClick(View view)
    {
        try
        {
            /*Save the assignment name*/
            EditText et = findViewById(R.id.assignmentNameID);
            assignment.setTitle(et.getText().toString());

            /*Validate that both inputs that have been entered*/
            if (assignment.getTitle() != null && assignment.getDueDate() != null)
            {
                /*Add the assignment to the list of assignments for an specific course*/
                course.SetAssignment(assignment);

                /*Update the course in the course list so that it includes the new
                assignment in his list of assignments*/
                myCourses.set(retrieveCourseIdx, course);

                /*Process the Intent!*/
                Bundle bundle = new Bundle();
                bundle.putSerializable("courseList", myCourses);

                /*Fill the Intent object and get outta here!*/
                Intent i = new Intent(this, VisualizeCourseActivity.class);
                i.putExtras(bundle); //The serialized Course() object will be assigned to the intent
                i.putExtra("courseToInspect", retrieveCourseIdx);
                startActivity(i); //Go back to the Main Activity
            }
        }
        catch (Exception e)
        {
            Log.d("AddAssignmentActivity", "Something went wrong when saving the new assignment into the course");
            Log.d("AddAssignmentActivity", "Something went wrong when the user pressed the SAVE button");
        }
    } //end of function

} //end of class

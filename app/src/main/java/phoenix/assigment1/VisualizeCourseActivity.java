/*------------------------------------------------------------------
 * FiLE: VisualizeCourseActivity.java
 * VERSION: 03/Feb/2019
 * PROGRAMMERS:
 * Edgar Elias - 7515927
 * Daniel Baker - 6461982
 * Irina Alexandru - 7925423
 * Samuel Lyra - 7763881
 *
 * DESCRIPTION: The purpose of this file is to visualize/inspect all
 *              the assignments for an specific course. The user will
 *              also be able to add new assignments.
 *
 *              Deleting assignments is not supported on this version.
 * -----------------------------------------------------------------*/

package phoenix.assigment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

public class VisualizeCourseActivity extends AppCompatActivity
{

    /*For getting the list of contents from the intent*/
    StringBuilder strBuilder = new StringBuilder();
    ArrayList<Course> myCourses;
    Course course;
    int retrieveCourseIdx;

    /*----------------------------------------------------------
     * Function: onCreate()
     * Parameters: Bundle savedInstanceState
     * Description: This overridden function will get the
     *              Intent from the previous activity and
     *              insert content into the widgets.
     * Returns: Void
     * --------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("Visualize Course", "WE ARE ON CREATE()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualize_course);

        /*Get the information from the previous activity*/
        processIntent();

        /*Now that we can have the selected item we can replace the data into the widgets*/
        fillWidgets();

    }//end of onCreate()

    /*----------------------------------------------------------
     * Function: processIntent()
     * Parameters: None.
     * Description: The purpose of this function is to process
     *              the intent from the previous activity.
     * Returns: Void
     * --------------------------------------------------------*/
    protected  void processIntent()
    {
        Intent retrieveCourses = this.getIntent(); //get the intent from the previous activity
        try
        {
            /*Get the object from the intent*/
            Bundle getBundledObjects = retrieveCourses.getExtras();
            if((getBundledObjects != null) && (getBundledObjects.isEmpty() == false))
            {
                /*The following lines of code will get the list of all the current courses and
                 * the course index in the list that we want to manipulate*/
                myCourses = (ArrayList<Course>) getBundledObjects.getSerializable("courseList"); //Get the list of current courses
                retrieveCourseIdx = retrieveCourses.getIntExtra("courseToInspect", -1);
                if(retrieveCourseIdx != -1)
                {
                    course = myCourses.get(retrieveCourseIdx); //Get the selected course
                }
            }
        }
        catch (Exception e)
        {
            Log.d("VisualizeCourseActivity", "Something went wrong when processing the intent from the previous Activity - " + e.toString());
        }
    }//end of function

    /*----------------------------------------------------------
     * Function: processIntent()
     * Parameters: None.
     * Description: The purpose of this function is to fill the
     *              widgets with course's information and all
     *              the due assignments/tasks.
     * Returns: Void
     * --------------------------------------------------------*/
    protected void fillWidgets()
    {
        Log.d("VisualizeCourseActivity", "Filling up the widgets with the course information");
        try
        {
            /*Change the top text view of the activity*/
            TextView courseTxtV = (TextView)findViewById(R.id.courseTitleTxtID);
            courseTxtV.setText(course.GetCourseName());

            /*Check if the course has assignments.
             * If the course has assignments, we will
             * display them in the list Recycler View*/
            if(!course.GetAssignments().isEmpty())
            {
                Log.d("VisualizeCourseActivity", "The course has assignments, we will display them on the screen");

                /*For every assignment in a course, display on the screen*/
                for(Assignment assignment : course.GetAssignments())
                {
                    strBuilder.append(assignment.GetAssignmentDetails() + "\n\n");
                    TextView tv = findViewById(R.id.assignmentsInsertViewID);
                    tv.setText(strBuilder.toString());
                }
                Log.d("VisualizeCourseActivity", "All the course has assignments have been displayed on the screen");
            }
        }
        catch (Exception e)
        {
            Log.d("VisualizeCourseActivity", "Something went wrong when trying to fill up the widgets with the course information");
        }
    }

    /*----------------------------------------------------------
     * Function: goBackBtnClick()
     * Parameters: View view
     * Description: The purpose of this method is to transfer
     *              to the previous activity. In this case,
     *              the MainActivity.
     * Returns: Void.
     * --------------------------------------------------------*/
    protected void goBackBtnClick(View view)
    {
        try
        {
            /*Get the updated course*/
            Bundle bundle = prepareToLeave();
            if(!bundle.isEmpty())
            {
                /*Fill the Intent object and get outta here!*/
                Intent i = new Intent(this, MainActivity.class);
                i.putExtras(bundle); //The serialized Course() object will be assigned to the intent
                i.putExtra("courseToInspect", retrieveCourseIdx);//the index of the modified course
                startActivity(i); //Go back to the Main Activity
            }
        }
        catch (Exception e)
        {
            Log.d("VisualizeCourseActivity", "Something went wrong when the user pressed the BACK button");
        }
    }

    /*----------------------------------------------------------
     * Function: addAssignmentClick()
     * Parameters: View view
     * Description: The purpose of this method is to transfer
     *              to the activity that will add an assignment
     *              to the course.
     * Returns: Void.
     * --------------------------------------------------------*/
    public void addAssignmentClick(View view)
    {
        try
        {
            /*Get the updated course*/
            Bundle bundle = prepareToLeave();
            if(!bundle.isEmpty())
            {
                /*Fill the Intent object and transfer to the next activity*/
                Intent i = new Intent(this, AddAssignmentActivity.class);
                i.putExtras(bundle); //The serialized Course() object will be assigned to the intent
                i.putExtra("courseToInspect", retrieveCourseIdx); //the index of the modified course
                startActivity(i); //Go to the AddCourseActivity
            }
        }
        catch (Exception e)
        {
            Log.d("VisualizeCourseActivity", "Something went wrong when trying to transfer to the AddCourseActivity");
        }
    }

    /*----------------------------------------------------------
     * Function: processIntent()
     * Parameters: None.
     * Description: The purpose of this function is to bundle the
     *              update the ArrayList with the course that has
     *              been modified/updated with new assignments.
     *
     * Returns: The serialized bundle.
     * --------------------------------------------------------*/
    protected Bundle prepareToLeave()
    {
        /*Update the the ArrayList with the new modified course*/
        Bundle bundle = new Bundle();
        myCourses.set(retrieveCourseIdx, course);
        bundle.putSerializable("courseList", myCourses);

        return bundle;
    }

} //end of class
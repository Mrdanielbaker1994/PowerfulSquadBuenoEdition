/*------------------------------------------------------------------
 * FiLE: AddCourseActivity.java
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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddCourseActivity extends AppCompatActivity
{

    private Course myNewCourse = new Course(); //not singleton
    private Course courseIndexZero = new Course(); //not singleton
    private static String firstTime = "";

    /*This ArrayList will receive a list of all the current courses
    * registered in the application. Moreover, every time this
    * activity gets triggered, we will retrieve the ArrayList from
    * the MainActivity so that we can .add() the new course that
    * will be created on this activity.*/
    private ArrayList<Course> listOfCourses;


    /*----------------------------------------------------------
     * Function: onCreate()
     * Parameters: Bundle savedInstanceState
     * Description: This overridden function will get the
     *              Intent from the MainActivity
     * Returns: Void
     * --------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        /*Get the intent from the previous activity and
         * retrieve the list of all the current courses*/
        Intent fromMainActivity = this.getIntent();
        Bundle getBundledObjects = fromMainActivity.getExtras();
        listOfCourses = (ArrayList<Course>) getBundledObjects.getSerializable("courseList"); //get the list of previous courses from the main activity
    }

    /*----------------------------------------------------------
     * Function: saveBtnClick()
     * Parameters: View view
     * Description: The purpose of this method is to save the
     *              contents from the course name and colour type.
     * Returns: Void.
     * --------------------------------------------------------*/
    public void saveBtnClick(View view)
    {

        if(firstTime == "")
        {
            courseIndexZero.SetCourseName("Select a Course");
            listOfCourses.add(courseIndexZero);
            firstTime = "Not the first time";
        }

        /*First, we need to get and set the course name*/
        EditText et = findViewById(R.id.courseNameTxtID);
        myNewCourse.SetCourseName(et.getText().toString()); //Set the course name to the Course object

        /*Second, we need to get and set the colour type*/
        Spinner mySpinner = (Spinner) findViewById(R.id.spinnerID);
        String selectedColour = mySpinner.getSelectedItem().toString();
        myNewCourse.SetColourType(selectedColour); //Set the colour type to the Course object

        /*Third, Add the Course object to the ArrayList
        * - this ArrayList also contains a list of previous courses,
        *   we are just appending the new created course... */

        listOfCourses.add(myNewCourse); //there are existent courses

        /*Fourth, transfer to the MainActivity*/
        transferToMainActivity();
    }

    /*----------------------------------------------------------
     * Function: cancelBtnClick()
     * Parameters: View view
     * Description: The purpose of this method is to cancel the
     *              user's  activity on this activity screen
     *              and return them to the MainActivity screen.
     * Returns: Void.
     * --------------------------------------------------------*/
    public void cancelBtnClick(View view)
    {
        /*Serialize the ArrayList with the new added course
         *  - This will be sent through the Intent*/
        Bundle bundle = new Bundle();
        bundle.putSerializable("courseList", listOfCourses);

        /*Fill the Intent object and get outta here!*/
        Intent i = new Intent(this, MainActivity.class);
        i.putExtras(bundle); //The serialized Course() object will be assigned to the intent
        startActivity(i); //Go back to the Main Activity
    }

    /*----------------------------------------------------------
     * Function: cancelBtnClick()
     * Parameters: View view
     * Description: The purpose of this method is to transfer
     *              to the main activity.
     * Returns: Void.
     * --------------------------------------------------------*/
    private void transferToMainActivity()
    {
        /*Serialize the ArrayList with the new added course
        *  - This will be sent through the Intent*/
        Bundle bundle = new Bundle();
        bundle.putSerializable("courseList", listOfCourses);

        /*Fill the Intent object and get outta here!*/
        Intent i = new Intent(this, MainActivity.class);
        i.putExtras(bundle); //The serialized Course() object will be assigned to the intent
        startActivity(i); //Go back to the Main Activity
    }

    public void openConestogaClick(View view) {
        //conestogaBtn_ID
        //Log.d("MainActivity", "test");
        String url = "https://www.conestogac.on.ca/current-students/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }
}
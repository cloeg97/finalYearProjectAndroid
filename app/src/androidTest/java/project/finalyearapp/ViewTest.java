package project.finalyearapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static org.junit.Assert.*;

public class ViewTest {

    @Rule
    public ActivityTestRule<View> mActivityTestRule = new ActivityTestRule<View>(View.class);

    private View mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        android.view.View view = mActivity.findViewById(R.id.recycler_menu);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}
package ah.hathi.simpleweather;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class WeatherActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new WeatherFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(item.getItemId() == R.id.change_city){
    		showInputDialog();
    	}
    	return false;
    }
    
    private void showInputDialog(){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Change city");
		final EditText input = new EditText(this);
		input.setInputType(InputType.TYPE_CLASS_TEXT);
		builder.setView(input);
		builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				changeCity(input.getText().toString());
			}
		});
		builder.show();
    }
    
    public void changeCity(String city){
    	WeatherFragment wf = (WeatherFragment)getSupportFragmentManager()
    							.findFragmentById(R.id.container);
    	wf.changeCity(city);
    	new CityPreference(this).setCity(city);
    }
}

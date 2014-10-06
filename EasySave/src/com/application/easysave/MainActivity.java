package com.application.easysave;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Map<Integer, Integer> editTextMap = new HashMap<Integer, Integer>();

	private final TextWatcher editTextWatcher = new TextWatcher() {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// System.out.println(s + " " + start + " " + before + " " + count);

		}

		@Override
		public void afterTextChanged(Editable s) {
		}

		private final boolean isPhoneNumber(CharSequence charSequence) {
			if (charSequence == null || TextUtils.isEmpty(charSequence)) {
				return false;
			} else {
				return android.util.Patterns.PHONE.matcher(charSequence)
						.matches();
			}
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		EditText ed;
		Button button;
		TableLayout tableLayout = (TableLayout) findViewById(R.id.easySaveTableLayout);

		TableRow tableRow;
		int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				(float) 1, this.getResources().getDisplayMetrics());
		for (int i = 0; i < 50; i++) {
			tableRow = new TableRow(this);
			tableRow.setPadding(8, 8, 8, 8);
			tableRow.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			ed = new EditText(this);
			ed.setId(i);
			ed.setWidth(210 * dip);
			ed.addTextChangedListener(editTextWatcher);
			editTextMap.put(i, 0);
			ed.setHint("Enter the text here");
			ed.setText("");
			ed.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			button = new Button(this);
			button.setId(i);
			button.setText("Discard");
			button.setWidth(100 * dip);
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					int id = ((Button) v).getId();
					EditText editText = (EditText) findViewById(id);
					if (!"".equals(editText.getText()) && null != editText.getText()) {
						editText.setText("");
						Toast toast = Toast.makeText(getApplicationContext(),
								"Text Erased!", Toast.LENGTH_SHORT);
						toast.show();
					}
				}
			});
			button.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			tableRow.addView(ed);
			tableRow.addView(button);

			tableLayout.addView(tableRow, new TableLayout.LayoutParams(
					TableLayout.LayoutParams.WRAP_CONTENT,
					TableLayout.LayoutParams.WRAP_CONTENT));
		}
	}
}

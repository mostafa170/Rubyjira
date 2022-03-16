package com.devartlab.rubyjira.app.utilsView;
import static com.devartlab.rubyjira.data.utils.StringExpression.replaceArabicNumbers;
import android.content.Context;
import android.util.AttributeSet;

public class TextViewWithArabicDigits extends androidx.appcompat.widget.AppCompatTextView {
    public TextViewWithArabicDigits(Context context) {
        super(context);
    }

    public TextViewWithArabicDigits(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(replaceArabicNumbers(text), type);
    }
}

package net.aeronica.libs.mxmml.antlr.core;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class TestErrorDialog
{
    private TestErrorDialog() { /*NOP */ }

    public static class DialogListener extends BaseErrorListener
    {
        private DialogListener() { /* NOP */ }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
        {
            List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
            Collections.reverse(stack);
            StringBuilder buf = new StringBuilder();
            buf.append("rule stack: " + stack + " ");
            buf.append("line " + line + ":" + charPositionInLine + " at " + offendingSymbol + ": " + msg);

            JDialog dialog = new JDialog();
            Container contentPane = dialog.getContentPane();
            contentPane.add(new JLabel(buf.toString()));
            contentPane.setBackground(Color.white);
            dialog.setTitle("Syntax error");
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
    }
//     public static void main(String[] args) throws Exception {
//     ANTLRInputStream input = new ANTLRInputStream(System.in);
//     SimpleLexer lexer = new SimpleLexer(input);
//     CommonTokenStream tokens = new CommonTokenStream(lexer);
//     SimpleParser parser = new SimpleParser(tokens);
//     parser.addErrorListener(new DialogListener());
//     parser.prog();
//     }
}


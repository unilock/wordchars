package cc.unilock.wordchars.mixin;

import cc.unilock.wordchars.Wordchars;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextFieldWidget.class)
public class TextFieldWidgetMixin {
	@Shadow
	private String text;

	/**
	 * @author unilock
	 * @reason I don't feel like figuring this out right now lol
	 */
	@Overwrite
	private int getWordSkipPosition(int wordOffset, int cursorPosition, boolean skipOverSpaces) {
		int curPos = cursorPosition;
		boolean backwards = wordOffset < 0;
		int maxWordSkips = Math.abs(wordOffset);

		for(int i = 0; i < maxWordSkips; i++) {
			if (!backwards) {
				int length = this.text.length();
				int index = StringUtils.indexOfAny(this.text.substring(curPos), Wordchars.ARRAY);
				if (index == -1) {
					curPos = length;
				} else {
					curPos += index;
					while (skipOverSpaces && curPos < length && Wordchars.isWordchar(this.text.charAt(curPos))) {
						curPos++;
					}
				}
			} else {
				while (skipOverSpaces && curPos > 0 && Wordchars.isWordchar(this.text.charAt(curPos - 1))) {
					curPos--;
				}

				while (curPos > 0 && !Wordchars.isWordchar(this.text.charAt(curPos - 1))) {
					curPos--;
				}
			}
		}

		return curPos;
	}
}

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
		int i = cursorPosition;
		boolean bl = wordOffset < 0;
		int j = Math.abs(wordOffset);

		for(int k = 0; k < j; ++k) {
			if (!bl) {
				int l = this.text.length();
				i += StringUtils.indexOfAny(this.text.substring(i), Wordchars.ARRAY);
				if (i == -1) {
					i = l;
				} else {
					while(skipOverSpaces && i < l && Wordchars.isWordchar(this.text.charAt(i))) {
						++i;
					}
				}
			} else {
				while(skipOverSpaces && i > 0 && Wordchars.isWordchar(this.text.charAt(i - 1))) {
					--i;
				}

				while(i > 0 && !Wordchars.isWordchar(this.text.charAt(i - 1))) {
					--i;
				}
			}
		}

		return i;
	}
}

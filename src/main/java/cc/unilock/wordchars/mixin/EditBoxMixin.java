package cc.unilock.wordchars.mixin;

import cc.unilock.wordchars.Wordchars;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.EditBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EditBox.class)
public class EditBoxMixin {
	@WrapOperation(method = {"getPreviousWordAtCursor", "getNextWordAtCursor", "getWordEndIndex"}, at = @At(value = "INVOKE", target = "Ljava/lang/Character;isWhitespace(C)Z"))
	private boolean isWhitespace(char ch, Operation<Boolean> original) {
		return Wordchars.isWordchar(ch, original);
	}
}

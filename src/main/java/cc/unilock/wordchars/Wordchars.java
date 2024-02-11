package cc.unilock.wordchars;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

import java.util.List;
import java.util.stream.Collectors;

public class Wordchars {
	// $WORDCHARS on zsh
	// + space
	public static final String STRING = " "+"*?_-.[]~=/&;!#$%^(){}<>";
	public static final char[] ARRAY = STRING.toCharArray();
	public static final List<Character> LIST = STRING.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

	public static boolean isWordchar(char ch) {
		return LIST.contains(ch);
	}

	public static boolean isWordchar(char ch, Operation<Boolean> original) {
		return original.call(ch) || LIST.contains(ch);
	}
}

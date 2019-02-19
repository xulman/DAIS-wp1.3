/*
 * To the extent possible under law, the ImageJ developers have waived
 * all copyright and related or neighboring rights to this tutorial code.
 *
 * See the CC0 1.0 Universal license for details:
 *     http://creativecommons.org/publicdomain/zero/1.0/
 */
package de.mpicbg.ulman;

import net.imagej.ImageJ;

public class OpenFiji
{
	public static void main(final String... args)
	{
		final ImageJ ij = new net.imagej.ImageJ();
		ij.ui().showUI();
	}
}

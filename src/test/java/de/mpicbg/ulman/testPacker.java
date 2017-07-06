/*
 * To the extent possible under law, the ImageJ developers have waived
 * all copyright and related or neighboring rights to this tutorial code.
 *
 * See the CC0 1.0 Universal license for details:
 *     http://creativecommons.org/publicdomain/zero/1.0/
 */
package de.mpicbg.ulman;

import net.imagej.Dataset;
import net.imagej.ImageJ;
import net.imagej.ImgPlus;
import net.imglib2.Cursor;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.type.numeric.integer.UnsignedShortType;
import de.mpicbg.ulman.ImgPacker;

import io.scif.config.SCIFIOConfig;
import io.scif.config.SCIFIOConfig.ImgMode;
import io.scif.img.ImgIOException;
import io.scif.img.ImgOpener;

import org.zeromq.ZMQ;

public class testPacker
{
	@SuppressWarnings("unchecked")
	public static void main(final String... args)
	{
		final ImageJ ij = new net.imagej.ImageJ();
		//ij.ui().showUI();

		try {
			//read the input image
/*
			final SCIFIOConfig openingRegime = new SCIFIOConfig();
			openingRegime.imgOpenerSetImgModes(ImgMode.ARRAY);
			ImgOpener imgOpener = new ImgOpener();
			@SuppressWarnings("rawtypes")
			ImgPlus imgPlus = imgOpener.openImgs("/Users/ulman/DATA/combinedGT__009.tif",openingRegime).get(0);

/*
			Dataset ds = ij.dataset().open("/Users/ulman/DATA/combinedGT__009.tif");
			@SuppressWarnings("rawtypes")
			ImgPlus imgPlus = ds.getImgPlus();
*/

			Img<UnsignedShortType> img =
					new ArrayImgFactory<UnsignedShortType>().create(new int[] {50, 50}, new UnsignedShortType());
			Cursor<UnsignedShortType> c = img.cursor();
			while (c.hasNext()) c.next().set(97);
			ImgPlus<UnsignedShortType> imgPlus = new ImgPlus<>(img);

			//init the writing socket (but not bind it)
			ZMQ.Context zmqContext = ZMQ.context(1);
			ZMQ.Socket writerSocket = zmqContext.socket(ZMQ.PUSH);
			writerSocket.connect("tcp://localhost:5555");

			//start up the packer class
			final ImgPacker<UnsignedShortType> ip = new ImgPacker<>();
			ip.packAndSend(imgPlus, writerSocket);

			//clean up
			writerSocket.close();
			zmqContext.term();
		}
		catch (ImgIOException e) {
			//ij.log().error(e.getMessage());
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			//ij.log().error(e.getMessage());
			System.out.println(e.getMessage());
		}

		//and quit
		//ij.appEvent().quit();
	}
}
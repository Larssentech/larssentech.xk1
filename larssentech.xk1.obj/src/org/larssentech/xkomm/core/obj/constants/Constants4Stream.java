/*
 * Copyright 2014-2024 Larssentech Developers
 * 
 * This file is part of XKomm.
 * 
 * XKomm is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * XKomm is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * XKomm. If not, see <http://www.gnu.org/licenses/>.
 */

package org.larssentech.xkomm.core.obj.constants;

public interface Constants4Stream extends Constants4API {

	String COPY_PATH = HOME + SEP;
	int HEADER_LENGTH = 256;
	String XML_FILE_TAG = "file";
	String FILE_NAME_TAG = "fileName";
	String BLOCK_SIZE_TAG = "blockSize";
	String FILE_SIZE_TAG = "fileSize";
	String BLOCK_NUM_TAG = "blockNum";
	String NUM_BLOCKS_TAG = "numBlocks";
	String RECEIVING_SAVING = "\n" + "Receiving file..." + "\n" + "Saving to: " + "\n";
	String BYTES = "\n" + "Bytes: " + "\n";
	String BLOCK = "\n" + "Blocks: " + "\n";
	String DLOAD_PROGRESS = "Download progress: ";
	String PER100 = "%";
	String PER100_PROGRESS = "%...";
	String DATA_BLOCKS = "Data blocks: ";
	String OF = " of ";
	String BAD_DOWNSTREAM_SPEC = "Bad stream detected... ignoring data...";
	int BLOCK_BYTES = 64000;
	String FILE_HEADER = "==================";
	long MAX_OFFLINE_FILE_SIZE = 1100000;
	int GOOD_FILE = 0;
	int BAD_FILE = 1;
	int OFFLINE_SMALL_FILE = 2;
	int NO_IDEA = 3;
	int OFFLINE_BIG_FILE = 4;
	String WAITING_4_NET = " - Streamer waiting for available bandwidth...";
	String TO_QUEUE = " --> ";
	String STREAMING_CANCELLED = "\nStreaming Cancelled at block ";
	String DONE_STREAMING = "\nDone Streaming ";
	String DATA_SENT_1 = "Stream has left the sender. ";
	String DATA_SENT_2 = " blocks processed. Stream in transit...";
	String RECEIVING_1 = " is sending you an encrypted file stream: '";
	String RECEIVING_2 = "'. " + "Size: ";
	String RECEIVING_3 = " MB";
	String SENDER_CANCELLED = "User cancelled the stream, stopping...";
	int HUNDRED = 100;
	String SEND_PROGRESS_1 = " (";
	String SEND_PROGRESS_2 = " of ";
	String SEND_PROGRESS_3 = ")";
	String UPSTREAM_REMOVED = "Upstream Spec removed: ";
	String UPSTREAM_PROGRESS = "Stream Spec reports progress: ";
}
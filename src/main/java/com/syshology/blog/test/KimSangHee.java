package com.syshology.blog.test;

import java.util.Arrays;

public class KimSangHee {

	public String tt(int U, int L, int[] C) {

		int columsSumArray[] = C;
		String upper = "";
		String lower = "";
		int upperSum = 0;
		int lowerSum = 0;

		for (int i : columsSumArray) {
			int colSum = i;
			if (colSum == 1) {
				if (upperSum < U && lowerSum < L) {
					if (U > L) {
						upper = upper + "1";
						lower = lower + "0";
						upperSum++;
					} else if (U < L) {
						upper = upper + "0";
						lower = lower + "1";
						lowerSum++;
					} else {
						upper = upper + "1";
						lower = lower + "1";
						upperSum++;
						lowerSum++;
					}
				} else if (upperSum >= U && lowerSum < L) {
					upper = upper + "0";
					lower = lower + "1";
					lowerSum++;
				} else if (lowerSum >= L && upperSum < U) {
					upper = upper + "0";
					lower = lower + "1";
					lowerSum++;
				}

			} else if (colSum == 2) {
				upper = upper + "1";
				lower = lower + "1";
				lowerSum++;
				upperSum++;
			} else if (colSum == 0) {
				upper = upper + "0";
				lower = lower + "0";
			}
		}

		if (upperSum == U && lowerSum == L) {
			return upper + "," + lower;
		}
		System.out.println();
		return "IMPOSSIBLE";

	}

	public String line(String S) {
		String[] lines = S.split(System.getProperty("line.separator"));
		String response = "music size1b\r\n" + "images size2b\r\n" + "movies size3b\r\n" + "other size4b";
		int musicSize = 0;
		int imageSize = 0;
		int moveSize = 0;
		int otherSize = 0;
		for (String line : lines) {
			String[] value = line.split(" ");
			String fileName = value[value.length - 2];
			String readSize = value[value.length - 1];
			String tempSize = readSize.substring(0, readSize.length() - 1);
			int size = Integer.parseInt(tempSize);
			int index = fileName.lastIndexOf(".") + 1;
			String fileType = fileName.substring(index).toLowerCase();
			if (fileType.equals("mp3") || fileType.equals("aac") || fileType.equals("flac")) {
				musicSize = musicSize + size;
			} else if (fileType.equals("jpg") || fileType.equals("bmp") || fileType.equals("gif")) {
				imageSize = imageSize + size;
			} else if (fileType.equals("mp4") || fileType.equals("avi") || fileType.equals("mkv")) {
				moveSize = moveSize + size;
			} else {
				otherSize = otherSize + size;
			}
		}
		response = response.replace("size1", musicSize + "");
		response = response.replace("size2", imageSize + "");
		response = response.replace("size3", moveSize + "");
		response = response.replace("size4", otherSize + "");
		return response;
	}

	public int sort(int[] A) {
		int data[] = A;
		int size = data.length;
		int max;
		int temp;
		for (int i = 0; i < size - 1; i++) {
			max = i;
			for (int j = i + 1; j < size; j++) {
				if (data[max] < data[j]) {
					max = j;
				}
			}
			temp = data[max];
			data[max] = data[i];
			data[i] = temp;
		}
		int result = 0;
		for (int i : data) {
			if (i <= 0) {
				break;
			}
			for (int j = 1; j < data.length; j++) {
				int k = data[j];
				if ((i + k) == 0) {
					result = i;
					return result;
				}
			}
		}
		return 0;
	}

	public int solution(int[] A, int[] B) {
		int n = A.length;
		int m = B.length;
		;
		Arrays.sort(A);
		Arrays.sort(B);
		int i = 0;
		for (int k = 0; k < n; k++) {
			if (i < m && B[i] < A[k])
				i += 1;
			if (A[k] == B[i])
				return A[k];
		}
		return -1;
	}

}

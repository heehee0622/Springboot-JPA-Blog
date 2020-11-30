package com.syshology.blog;

import com.syshology.blog.test.KimSangHee;

public class Test {

	@org.junit.jupiter.api.Test
	public void testTT() {
		KimSangHee k = new KimSangHee();
		int C [] = {2,1,1,0,1};
		k.tt(3,2, C);
	}
	
	@org.junit.jupiter.api.Test
	public void testLine() {
		String value = "my.song.mp3 11b\r\n"
		+ "greatSong.flac 1000b\r\n"
		+ "video.mp4 200b\r\n"
		+ "game.exe 100b \r\n"
		+ "mov! e.mkv 10000b";
		KimSangHee k = new KimSangHee();
		k.line(value);
	}
	
	@org.junit.jupiter.api.Test
	public void sort() {
		KimSangHee k = new KimSangHee();
		int data[] = {66, 10, 1, 99, 5,-10, -1,-66};
		int result = k.sort(data);
		System.out.print(result);
	}
	@org.junit.jupiter.api.Test
	public void modify() {
		KimSangHee k = new KimSangHee();
		int data1[] = {1, 100, 1};
		int data2[] = {4, 5,7,99,100};
		int result = k.solution(data1, data2);
		System.out.print(result);
	}
}

package com.perfumepedia.PerfumePedia.MockObject;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Getter
@Setter
public class MockPerfumeDto {
    private List<MockPerfume> items;

    public MockPerfumeDto(){
        items = new ArrayList<>();

        IntStream.range(0, 10).forEach(i->{
            addFirstPerfume();
            addSecondPerfume();
            addThirdPerfume();
            addFourthPerfume();
            addFifthPerfume();
            addSixthPerfume();
        });
    }

    private void addFirstPerfume(){
        MockPerfume mockPerfume = new MockPerfume();

        mockPerfume.setUuid("123");
        mockPerfume.setPerfume_name("오 드 퍼퓸 필로시코스");
        mockPerfume.setBrand_name("딥디크");
        mockPerfume.setImage_path("http://49.50.162.194:3000/img/deptyque1.png");

//        String[] top = "페티그레인, 블랙 페퍼, 프랑킨센스".split(", ");
//        for (String s : top) {
//            MockNote note = new MockNote(s);
//            mockPerfume.addTopNote(note);
//        }
//
//        String[] mid = "쿠민, 시더, 프랑킨센스".split(", ");
//        for (String s : mid) {
//            MockNote note = new MockNote(s);
//            mockPerfume.addMiddleNote(note);
//        }
//
//        String[] base = "샌달우드, 시더, 베티버".split(", ");
//        for (String s : base) {
//            MockNote note = new MockNote(s);
//            mockPerfume.addBaseNote(note);
//        }
//
//        String[] single = "".split(" ");
//        for (String s : single) {
//            MockNote note = new MockNote(s);
//            mockPerfume.addSingleNote(note);
//        }

        items.add(mockPerfume);
    }
    private void addSecondPerfume(){
        MockPerfume mockPerfume = new MockPerfume();

        mockPerfume.setUuid("242");
        mockPerfume.setPerfume_name("쿼커스");
        mockPerfume.setBrand_name("펜할리곤스");
        mockPerfume.setImage_path("http://49.50.162.194:3000/img/penhaligons1.png");

        items.add(mockPerfume);
    }
    private void addThirdPerfume(){
        MockPerfume mockPerfume = new MockPerfume();

        mockPerfume.setUuid("600");
        mockPerfume.setPerfume_name("오 드 퍼퓸 도손");
        mockPerfume.setBrand_name("딥디크");
        mockPerfume.setImage_path("http://49.50.162.194:3000/img/deptyque2.png");

        items.add(mockPerfume);
    }
    private void addFourthPerfume(){
        MockPerfume mockPerfume = new MockPerfume();

        mockPerfume.setUuid("543");
        mockPerfume.setPerfume_name("블렘하임 부케");
        mockPerfume.setBrand_name("펜할리곤스");
        mockPerfume.setImage_path("http://49.50.162.194:3000/img/penhaligons2.png");

        items.add(mockPerfume);
    }
    private void addFifthPerfume(){
        MockPerfume mockPerfume = new MockPerfume();

        mockPerfume.setUuid("700");
        mockPerfume.setPerfume_name("오 드 퍼퓸 오르페옹");
        mockPerfume.setBrand_name("딥디크");
        mockPerfume.setImage_path("http://49.50.162.194:3000/img/deptyque3.png");

        items.add(mockPerfume);
    }
    private void addSixthPerfume(){
        MockPerfume mockPerfume = new MockPerfume();

        mockPerfume.setUuid("222");
        mockPerfume.setPerfume_name("릴리 오브 더 밸리");
        mockPerfume.setBrand_name("펜할리곤스");
        mockPerfume.setImage_path("http://49.50.162.194:3000/img/penhaligons3.jpg");

        items.add(mockPerfume);
    }
}

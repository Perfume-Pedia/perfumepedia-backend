package MockService;

import MockObject.MockNote;
import MockObject.MockPerfume;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MockPerfumeService {
    List<MockPerfume> mockPerfumes;

    public MockPerfumeService() {
        mockPerfumes = new ArrayList<>();

        IntStream.range(0, 10).forEach(i->{addOnePerfume();});
    }

    private void addOnePerfume(){
        MockPerfume mockPerfume = new MockPerfume();

        mockPerfume = new MockPerfume(); 
        mockPerfume.setName("이더시스 오 드 퍼퓸");
        mockPerfume.setBrand_name("이솝");
        mockPerfume.setPrice("210,000");
        mockPerfume.setUrl("https://www.aesop.com/kr/p/fragrance/eidesis/eidesis-eau-de-parfum/");
        mockPerfume.setImage_url("https://www.aesop.com/u1nb1km7t5q7/6otLbkgZuAv2oDJ3UG3r53/752eb0a5fe1f836a105247851d0200b8/Aesop_Fragrance_Eidesis_Eau_de_Parfum_50mL_Web_Front_Large_900x878px.png");
        mockPerfume.setBrand_url("https://www.aesop.com/kr/");

        String[] top = "페티그레인, 블랙 페퍼, 프랑킨센스".split(", ");
        for (String s : top) {
            MockNote note = new MockNote(s);
            mockPerfume.addTopNote(note);
        }

        String[] mid = "쿠민, 시더, 프랑킨센스".split(", ");
        for (String s : mid) {
            MockNote note = new MockNote(s);
            mockPerfume.addMiddleNote(note);
        }

        String[] base = "샌달우드, 시더, 베티버".split(", ");
        for (String s : base) {
            MockNote note = new MockNote(s);
            mockPerfume.addBaseNote(note);
        }

        String[] single = "".split(" ");
        for (String s : single) {
            MockNote note = new MockNote(s);
            mockPerfume.addSingleNote(note);
        }

        mockPerfumes.add(mockPerfume);
    }

}

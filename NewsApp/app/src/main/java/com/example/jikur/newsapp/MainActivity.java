package com.example.jikur.newsapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ListView mNewsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsListView=(ListView)findViewById(R.id.listView);
        new NewsTask().execute("http://fs.jtbc.joins.com/RSS/newsflash.xml");
    }

    //AsynTask
    class NewsTask extends AsyncTask<String, String, NewsBean> {

        private ProgressDialog prd;

        @Override
        protected void onPreExecute() {
            prd = new ProgressDialog(MainActivity.this);
            prd.setMessage("뉴스를 가져오는 중 입니다...");
            prd.setCancelable(false);
            prd.show();
        }

        @Override
        protected NewsBean doInBackground(String... params) {
            StringBuilder output = new StringBuilder();

            try {
                URL url = new URL(params[0]);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = null;
                while (true) {
                    line = reader.readLine();
                    if (line == null) break;
                    output.append(line + "\n");
                }
                reader.close();

                String xmlData = output.toString();
                GsonXml gsonXml = new GsonXmlBuilder().setXmlParserCreator(parserCreator).setSameNameLists(true).create();
                xmlData = "<rss version=\"2.0\"><channel><title>JTBC News</title><link>http://fs.jtbc.joins.com/RSS/newsflash.xml</link><description>속보 RSS</description><language>ko</language><copyright>Copyright(C) JTBC Contents Hub. All rights reserved.</copyright><category>속보</category><pubDate>2017년 7월 10일 월요일 오후 3:25:00</pubDate><item><title>[영상구성] 문 대통령의 '4박 6일'…다자외교 성공 데뷔 </title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492909</link><description>문재인 대통령 G20 일정 마치고 귀국독일 G20 방문한 문 대통령의 '4박 6일'첫 날, 메르켈 총리와 정상회담[북한의 도발을 멈추기 위해서 국제적으로 더욱 강도 높은 제재와 압박의 방안이 강구돼야 할 것 같습니다.</description><pubDate>2017.07.10</pubDate></item><item><title>오프닝</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492914</link><description>시청자 여러분, 안녕하십니까. 문재인 대통령이 독일 함부르크에서 열린 G20 정상회담을 마치고 오늘(10일) 오전 귀국했습니다. 문 대통령은 한·미·일 정상회담에서 북핵 사태에 대한 공동대처에 합의했고 시진핑 </description><pubDate>2017.07.10</pubDate></item><item><title>'최고의 사랑' 김숙, '숙시리즈' 전시회 성황리 개최 '웃음폭탄'</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492933</link><description>JTBC '님과 함께 시즌2-최고(高)의 사랑'에서 윤정수와 가상결혼 생활을 하고 있는 김숙이 온라인에서 유행하고 있는 '숙시리즈' 사진들을 모아 전시회를 개최했다. 지난 6월 김숙은 송은이와 함께 진행하고 있는 팟</description><pubDate>2017.07.10</pubDate></item><item><title>국회 미방위, 이효성 방통위원장 청문회 19일 실시</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492920</link><description>국회 미래창조과학방송통신위원회는 10일 이효성 방송통신위원장 후보자에 대한 인사청문회를 오는 19일 열기로 확정했다. 미방위는 이날 오전 전체회의를 열어 이 후보자에 대한 인사청문회 실시계획서 안건을 의결</description><pubDate>2017.07.10</pubDate></item><item><title>'비정상회담' 전 세계서 일어난 교통수단 사건사고 리포트!</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492912</link><description>'비정상회담'이 전 세계에서 일어나고 있는 교통수단 관련 사건사고에 대해 알아봤다.  먼저 자히드는 최근 200여명의 사상자가 발생한 유조차 전복 사건을 전하며, \"라마단이 끝난 걸 축하하는 '이드' 명절 전날이</description><pubDate>2017.07.10</pubDate></item><item><title>'냉장고를 부탁해' 이경규 \"내가 한국서 소시지 최초로 먹은 어린이\"</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492910</link><description>이경규가 어린 시절 음식에 얽힌 다양한 추억을 공개한다.  10일(월) 밤 9시 30분에 방송되는 JTBC '냉장고를 부탁해'에서는 예능 대부 이경규의 냉장고가 공개된다. 이날 이경규는 등장부터 MC와 셰프들에게 특유의</description><pubDate>2017.07.10</pubDate></item><item><title>'냉장고를 부탁해' 이경규 \"음식은 너무 맛있으면 안 된다\"?</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492830</link><description>예능 대부 이경규가 독특한 맛 철학으로 셰프들을 '멘붕'에 빠뜨렸다.  10일(월) 밤 9시 30분에 방송되는 JTBC '냉장고를 부탁해'에서는 이경규와 김준호가 출연, 대한민국 대표 희극인들의 냉장고가 공개된다.  이</description><pubDate>2017.07.10</pubDate></item><item><title>'효리네 민박' 3회 연속 시청률 경신…분당 최고 11.6%</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492828</link><description>JTBC '효리네 민박'이 3회 연속 자체최고 시청률 기록을 경신하며 동시간대를 장악했다. 9일 방송된 '효리네 민박' 3회는 8.2%(닐슨코리아 수도권 기준)의 시청률로 동시간대 비지상파 1위를 차지했다. 전국 기준 시</description><pubDate>2017.07.10</pubDate></item><item><title>'등번호 9번' 남기고 떠난 이병규…역대 13번째 영구 결번</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492705</link><description>[앵커]'적토마'라는 별명으로 유명한 야구선수죠. LG 이병규 선수가 20년 간 정들었던 그라운드에 작별을 고했습니다. 이병규의 등번호 9번은 영원히 빈 자리로 남겨 두기로 했습니다. 주정완 기자입니다.[기자]\"오,</description><pubDate>2017.07.10</pubDate></item><item><title>G20 회의서도 '사드 평행선'…경제 보복 장기화 우려</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492710</link><description>[앵커]앞서 G20 정상회의를 정치·외교적으로 풀어봤었는데요. 이번에는 경제적으로 어떤 영향이 있을지 한 번 짚어보겠습니다. 정철진 경제평론가 나오셨습니다. Q. 사드 해결 안 돼…경제 보복 장기화 우려 Q. 5월</description><pubDate>2017.07.10</pubDate></item><item><title>이준서 검찰 수사 후폭풍…불 붙은 '미필적 고의' 논란</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492728</link><description>[앵커]이제 국민의당 이준서 전 최고위원이 구속될 것인가, 그리고 검찰 수사 결과는 어떻게 나올 것인가에 따라서 정치권에 후폭풍이 상당할 것으로 보입니다. 김광삼 변호사, 최영일 시사평론가와 함께 말씀나누겠</description><pubDate>2017.07.10</pubDate></item><item><title>문 대통령, 외교무대 성공적 데뷔…대북 정책 전망은?</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492719</link><description>[앵커]이번에는 전문가와 이번 G20 회의 얘기 좀 더 해보겠습니다. 중앙일보의 채인택 국제 전문기자 나왔습니다. Q. 문 대통령, 국제 외교무대 성공적 데뷔Q. 사상 첫 한·미·일 정상 공동성명 주목Q. G20 정상회</description><pubDate>2017.07.10</pubDate></item><item><title>추미애 겨냥한 국민의당…\"검찰 수사 가이드라인 제시\"</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492730</link><description>[앵커]이준서 전 최고위원에 대해 검찰이 구속영장을 청구한 것에 대해 국민의당은 앞서 미필적 고의를 언급했었던 추미애 민주당 대표를 겨냥해서 반발했습니다. 민주당은 국민의당 어느 누구도 책임지지 않고 공세</description><pubDate>2017.07.10</pubDate></item><item><title>'비정상회담' 배우 서신애 \"너무 어려 보여서 고민인 나\"</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492816</link><description>'비정상회담'에 소녀에서 숙녀로 돌아온 배우 서신애가 출연해 \"너무 어려 보여서 고민인 나, 비정상인가요?\"를 안건으로 이야기를 나누었다. 서신애는 \"최근에도 초등학생으로 오해하는 사람이 있었다\"며 이런 안건</description><pubDate>2017.07.10</pubDate></item><item><title>'비긴어게인' 최고의 1분은?…'비긴 어스'의 첫 버스킹</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492807</link><description>'비긴 어스' 멤버들의 첫 버스킹 장면이 분당 9%를 육박하는 최고 시청률을 기록했다.  9일(일) 밤 10시 30분에 방송된 JTBC '비긴어게인'이 시청률 7.4%(닐슨 코리아 수도권 유료가구 기준)를 기록하며 자체 최고시</description><pubDate>2017.07.10</pubDate></item><item><title>미 캘리포니아, 기록적 폭염에…산불·정전까지 '3중고'</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492707</link><description>[앵커]미국 캘리포니아 주가 기록적인 폭염에 산불까지 이어져 피해가 늘고 있습니다. 로스앤젤레스 도심 지역은 기온이 37도에 육박해서 131년 만에 최고를 기록했습니다. 부소현 특파원입니다. [기자]마치 붉은 조</description><pubDate>2017.07.10</pubDate></item><item><title>검은 개 차별 이제 그만…청와대로 입양된 '토리' 관심</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492708</link><description>[앵커]2년이 넘게 보호센터에서 입양이 되지 못한 채로 살던 검은색 털을 가진 유기견이 이제 청와대로 들어갑니다. 이 사연을 계기로 동물 단체부터 화가와 작가까지 검은 개 입양 프로젝트에 나섰습니다.서효정 기</description><pubDate>2017.07.10</pubDate></item><item><title>소득세 최고세율 대상 5억→3억 검토…'부자 증세' 시동</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492711</link><description>당정이 내년 세법 개정안을 마련하면서 '부자 증세'를 위해 소득세 최고세율 적용 구간을 지금보다 확대하는 방안을 검토하고 있습니다.현행 소득세 최고세율은 40%로, 과표가 5억 원이 넘을 경우 적용되는데 이를 3</description><pubDate>2017.07.10</pubDate></item><item><title>스스로 권리 찾기 나선 '을'…분쟁조정 신청 26% 급증</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492712</link><description>[앵커]공정거래위원회와 검찰이 최근 프랜차이즈 본사의 '갑질'을 겨냥하고 있죠. 그런데 가맹점 스스로도 권리 찾기에 나섰습니다. 올 들어 가맹점들이 본사를 상대로 낸 분쟁 조정 신청은 지난해보다 26%나 늘었습</description><pubDate>2017.07.10</pubDate></item><item><title>어린 뱀 잡아 새끼에…멸종위기종 '팔색조' 최초 포착</title><link>http://news.jtbc.joins.com/article/article.aspx?news_id=NB11492713</link><description>[앵커]8가지 빛깔의 팔색조, 멸종위기종입니다. 여름철새인 이 팔색조가 최근 우리나라에 와서 서식을 하고 있는데요. 어미 새가 지렁이가 아니라 뱀까지 물고 와서 새끼에게 먹이는 장면이 이번에 포착이 됐습니다.</description><pubDate>2017.07.10</pubDate></item></channel></rss>\n";

                NewsBean newsBean = gsonXml.fromXml(xmlData, NewsBean.class);
                return newsBean;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        //Parser Creator
        XmlParserCreator parserCreator = new XmlParserCreator() {
            @Override
            public XmlPullParser createParser() {
                try {
                    return XmlPullParserFactory.newInstance().newPullParser();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        @Override
        protected void onPostExecute(NewsBean newsBean) {
            prd.dismiss();

            if(newsBean!=null)
            {
                //1. 어댑터 생성
                NewsAdapter newsAdapter=new NewsAdapter(MainActivity.this, newsBean);
                //2. setAdapter()
                mNewsListView.setAdapter(newsAdapter);
            }
        }
    }

}

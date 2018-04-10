package com.neueda.urlshortener.app;

import com.neueda.urlshortener.data.entity.NeuedaUrl;
import com.neueda.urlshortener.data.model.NeuedaUrlModel;
import com.neueda.urlshortener.data.repo.UrlMongoRepo;
import com.neueda.urlshortener.data.service.IUrlService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlShortenerTest {

   @Autowired
   IUrlService iUrlService;

   @Mock
   UrlMongoRepo urlMongoRepo;

   NeuedaUrlModel voidModel;

   @Before
   public void setUp() throws Exception {
	  voidModel = new NeuedaUrlModel("","","");
   }

   @After
   public void tearDown() throws Exception {
   }

// @Test
// public void testMain() {
//    fail("Not yet implemented");
// }

   @Test
   public void createUrl() throws Exception {
      NeuedaUrl url = iUrlService.createUrl(voidModel);
      Assert.assertEquals(5, url.getShortUrl().length());
   }


   @Test
   public void updateUrl() throws Exception {
      NeuedaUrl url = iUrlService.createUrl(voidModel);
      List<NeuedaUrl> neuedaUrlList = new ArrayList<>();
      neuedaUrlList.add(url);
      when(urlMongoRepo.findByShortUrl(url.getShortUrl())).thenReturn(neuedaUrlList);
      NeuedaUrlModel secondModel = new NeuedaUrlModel("New Long Url","new title","");
      secondModel.setShortUrl(url.getShortUrl());
      NeuedaUrl updatedUrl = iUrlService.updateUrl(secondModel);

      Assert.assertEquals(updatedUrl.getShortUrl(), url.getShortUrl());
      Assert.assertNotEquals(updatedUrl.getLongUrl(), url.getLongUrl());
      Assert.assertNotEquals(updatedUrl.getUrlTitle(), url.getUrlTitle());
   }


}
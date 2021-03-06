package com.naveronics.alexa.skill.uvindex.service;

import com.naveronics.alexa.skill.uvindex.common.Constant;
import com.naveronics.alexa.skill.uvindex.repository.UvIndexRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class UvIndexServiceTest {
    private UvIndexServiceImpl service;
    @Mock
    private UvIndexRepository uvIndexRepository;

    @Parameterized.Parameters(name = "{index}: {0} is \"{1}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {-1, Constant.UvIndexLevelText.LOW},
                {0, Constant.UvIndexLevelText.LOW},
                {1, Constant.UvIndexLevelText.LOW},
                {2, Constant.UvIndexLevelText.LOW},
                {3, Constant.UvIndexLevelText.MODERATE},
                {4, Constant.UvIndexLevelText.MODERATE},
                {5, Constant.UvIndexLevelText.MODERATE},
                {6, Constant.UvIndexLevelText.HIGH},
                {7, Constant.UvIndexLevelText.HIGH},
                {8, Constant.UvIndexLevelText.VERY_HIGH},
                {9, Constant.UvIndexLevelText.VERY_HIGH},
                {10, Constant.UvIndexLevelText.VERY_HIGH},
                {11, Constant.UvIndexLevelText.EXTREME},
                {12, Constant.UvIndexLevelText.EXTREME},
                {100, Constant.UvIndexLevelText.EXTREME},
        });
    }

    @Before
    public void setUp() {
        uvIndexRepository = mock(UvIndexRepository.class);
        service = new UvIndexServiceImpl(uvIndexRepository);
    }

    @Parameterized.Parameter
    public int level;
    @Parameterized.Parameter(1)
    public String expectedLevelText;

    @Test
    public void getUvIndexLevelText() {
        when(uvIndexRepository.get()).thenReturn(level);
        assertEquals(expectedLevelText, service.getUvIndexLevelText());
    }
}
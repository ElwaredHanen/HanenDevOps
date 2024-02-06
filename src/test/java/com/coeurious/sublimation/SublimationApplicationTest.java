package com.coeurious.sublimation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SublimationApplicationTest {

	@Test
	void testApplication() {
		MockedStatic<SpringApplication> utilities = Mockito.mockStatic(SpringApplication.class);
		utilities.when((MockedStatic.Verification) SpringApplication.run(SublimationApplication.class, new String[]{})).thenReturn(null);
		SublimationApplication.main(new String[]{});
		Assertions.assertThat(SpringApplication.run(SublimationApplication.class, new String[]{})).isNull();
	}

}

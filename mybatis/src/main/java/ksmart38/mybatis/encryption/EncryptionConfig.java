package ksmart38.mybatis.encryption;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionConfig {

	@Bean("jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor pooledPBEStringEncryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig stringPBEConfig = new SimpleStringPBEConfig(); 
		
		stringPBEConfig.setPassword("ksmart38"); 			//대칭키(암호화키)
		stringPBEConfig.setAlgorithm("PBEWithMD5AndDES"); 	//사용할 암호화 알고리즘
		stringPBEConfig.setKeyObtentionIterations("1000");	//몇번을 반복해서 키를 얻을 지에 대한 설정
		stringPBEConfig.setPoolSize("1");
		stringPBEConfig.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");	//암호문이 랜덤으로 나올 수 있게 해주는 라이브러리
		stringPBEConfig.setStringOutputType("base64");		//암호문의 형태 64진법
		pooledPBEStringEncryptor.setConfig(stringPBEConfig);
        return pooledPBEStringEncryptor;
	}
}

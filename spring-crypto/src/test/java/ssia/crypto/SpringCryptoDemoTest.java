package ssia.crypto;//: ssia.crypto.SpringCryptoDemoTest.java


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@ExtendWith(MockitoExtension.class)
@DisplayName("Test Spring Crypto - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class SpringCryptoDemoTest {

    static final int LENGTH_OF_STRING_KEY = 16;
    static final int DEFAULT_BYTE_KEY_LENGTH = 8;

    @BeforeEach
    void setUp() {
    }


    @Nested
    class StringKeyGeneratorTest {

        private StringKeyGenerator skg;

        @BeforeEach
        void setUp() {
            this.skg = KeyGenerators.string();
        }

        @Test
        void able_To_Generate_String_Key_Containing_16_Characters() {

            // Given

            // When
            boolean sameLength = IntStream.range(0, 10)
                    .map(i -> this.keyLength())
                    .filter(length -> length != LENGTH_OF_STRING_KEY)
                    .findAny()
                    .isEmpty();

            // Then
            assertThat(sameLength).isTrue();
        }

        private int keyLength() {
            var key = this.skg.generateKey();
            log.info(">>> String Key: {}", key);
            return key.length();
        }
    }

    @Nested
    class ByteKeyGeneratorTest {

        static final int BYTE_KEY_LENGTH = 16;

        private BytesKeyGenerator defaultBkg;
        private BytesKeyGenerator bkg16;

        @BeforeEach
        void setUp() {
            this.defaultBkg = KeyGenerators.secureRandom();
            this.bkg16 = KeyGenerators.secureRandom(BYTE_KEY_LENGTH);
        }

        @Test
        void able_To_Generate_Byte_Key_Containing_8_Bytes() {

            // Given

            // When
            boolean sameLength = IntStream.range(0, 10)
                    .map(i -> this.keyLength(defaultBkg))
                    .filter(length -> length != this.defaultBkg.getKeyLength())
                    .findAny()
                    .isEmpty();

            // Then
            assertThat(sameLength).isTrue();
            assertThat(DEFAULT_BYTE_KEY_LENGTH).isEqualTo(
                    this.defaultBkg.getKeyLength());
        }

        @Test
        void able_To_Generate_Byte_Key_Containing_Specified_Length_Of_Bytes() {

            // Given

            // When
            boolean sameLength = IntStream.range(0, 10)
                    .map(i -> this.keyLength(bkg16))
                    .filter(length -> length != this.bkg16.getKeyLength())
                    .findAny()
                    .isEmpty();

            // Then
            assertThat(sameLength).isTrue();
            assertThat(BYTE_KEY_LENGTH).isEqualTo(this.bkg16.getKeyLength());
        }

        private int keyLength(@NonNull final BytesKeyGenerator bkg) {
            var key = bkg.generateKey();
            log.info(">>> Byte Key: {}", key);
            return key.length;
        }

    }


    @Nested
    class ByteEncryptorsTest {

        static final String VALUE_TO_ENCRYPT = "JAVA_17";

        private BytesEncryptor standardBytesEncryptor;
        private BytesEncryptor strongerBytesEncryptor;

        @BeforeEach
        void setUp() {
            String salt = KeyGenerators.string().generateKey();
            String password = "secret";
            this.standardBytesEncryptor = Encryptors.standard(password, salt);
            this.strongerBytesEncryptor = Encryptors.stronger(password, salt);
        }

        @Test
        void encrypt_Bytes_With_Different_Encryptors() {
            log.info(">>> Using Standard Byte Encryptor: ");
            encryptBytes(this.standardBytesEncryptor);
            log.info(">>> Using Stronger Byte Encryptor: ");
            encryptBytes(this.strongerBytesEncryptor);
        }

        private void encryptBytes(@NonNull final BytesEncryptor be) {

            // Given
            byte[] data = VALUE_TO_ENCRYPT.getBytes();
            log.info(">>> Data to Encrypt: {}", data);

            // When
            byte[] encrypted = be.encrypt(data);
            log.info(">>> Encrypted data: {}", encrypted);

            byte[] decrypted = be.decrypt(encrypted);
            log.info(">>> Decrypted data: {}", decrypted);

            // Then
            assertThat(decrypted).isEqualTo(data);
        }

    }

    @Nested
    class TextEncryptorsTest {

        static final String VALUE_TO_ENCRYPT = "JAVA_21";

        private String salt;
        private String password;

        @BeforeEach
        void setUp() {
            this.salt = KeyGenerators.string().generateKey();
            this.password = "secret";
        }

        @Test
        void noOp_Test_Encryptor() {

            // Given
            TextEncryptor noOpEncryptor = Encryptors.noOpText();

            // When
            String encrypted = noOpEncryptor.encrypt(VALUE_TO_ENCRYPT);

            // Then
            assertThat(encrypted).isEqualTo(VALUE_TO_ENCRYPT);
        }

        @Test
        void standard_Text_Encryptor() {

            // Given
            TextEncryptor standardTe = Encryptors.text(password, salt);

            // When
            String encrypted_1 = standardTe.encrypt(VALUE_TO_ENCRYPT);
            log.info(">>> Encrypts '{}' with Standard Text Encryptor {} ",
                    VALUE_TO_ENCRYPT, encrypted_1);

            String encrypted_2 = standardTe.encrypt(VALUE_TO_ENCRYPT);
            log.info(">>> Encrypts '{}' with Standard Text Encryptor {} ",
                    VALUE_TO_ENCRYPT, encrypted_2);

            String encrypted_3 = standardTe.encrypt(VALUE_TO_ENCRYPT);
            log.info(">>> Encrypts '{}' with Standard Text Encryptor {} ",
                    VALUE_TO_ENCRYPT, encrypted_3);

            // Then
            assertThat(encrypted_1)
                    .isNotEqualTo(encrypted_2)
                    .isNotEqualTo(encrypted_3);

            assertThat(standardTe.decrypt(encrypted_1))
                    .isEqualTo(VALUE_TO_ENCRYPT);

            assertThat(standardTe.decrypt(encrypted_2))
                    .isEqualTo(VALUE_TO_ENCRYPT);
        }

        @Test
        void delux_Text_Encryptor() {

            // Given
            TextEncryptor deluxTe = Encryptors.delux(password, salt);

            // When
            String encrypted = deluxTe.encrypt(VALUE_TO_ENCRYPT);
            log.info(">>> Encrypts '{}' with Delux Text Encryptor {} ",
                    VALUE_TO_ENCRYPT, encrypted);

            // Then
            assertThat(deluxTe.decrypt(encrypted)).isEqualTo(VALUE_TO_ENCRYPT);
        }

        @Test
        void queryable_Text_Encryptor() {

            // Given
            TextEncryptor te = Encryptors.queryableText(password, salt);

            // When
            String encrypted_1 = te.encrypt(VALUE_TO_ENCRYPT);
            log.info(">>> Encrypts '{}' with Queryable Text Encryptor {} ",
                    VALUE_TO_ENCRYPT, encrypted_1);

            String encrypted_2 = te.encrypt(VALUE_TO_ENCRYPT);
            log.info(">>> Encrypts '{}' with Queryable Text Encryptor {} ",
                    VALUE_TO_ENCRYPT, encrypted_2);

            String encrypted_3 = te.encrypt(VALUE_TO_ENCRYPT);
            log.info(">>> Encrypts '{}' with Queryable Text Encryptor {} ",
                    VALUE_TO_ENCRYPT, encrypted_3);

            // Then
            assertThat(encrypted_1)
                    .isEqualTo(encrypted_2)
                    .isEqualTo(encrypted_3);

            assertThat(te.decrypt(encrypted_1))
                    .isEqualTo(VALUE_TO_ENCRYPT);

            assertThat(te.decrypt(encrypted_2))
                    .isEqualTo(VALUE_TO_ENCRYPT);
        }

    }

}///:~
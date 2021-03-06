package com.alipay.mobilesecuritysdk.deviceID;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SecurityUtils
{
  public static final String TAG = "SecurityUtils";
  private static String str = new String("idnjfhncnsfuobcnt847y929o449u474w7j3h22aoddc98euk#%&&)*&^%#");

  private static void appendHex(StringBuffer paramStringBuffer, byte paramByte)
  {
    paramStringBuffer.append("0123456789ABCDEF".charAt(paramByte >> 4 & 0xF)).append("0123456789ABCDEF".charAt(paramByte & 0xF));
  }

  public static String decrypt(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new String(decrypt(getRawKey(paramString1.getBytes()), toByte(paramString2)));
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }

  private static byte[] decrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws Exception
  {
    paramArrayOfByte1 = new SecretKeySpec(paramArrayOfByte1, "AES");
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(2, paramArrayOfByte1, new IvParameterSpec(new byte[localCipher.getBlockSize()]));
    return localCipher.doFinal(paramArrayOfByte2);
  }

  public static String encrypt(String paramString1, String paramString2)
  {
    Object localObject = null;
    try
    {
      paramString1 = encrypt(getRawKey(paramString1.getBytes()), paramString2.getBytes());
      return toHex(paramString1);
    }
    catch (Exception paramString1)
    {
      while (true)
      {
        paramString1.printStackTrace();
        paramString1 = localObject;
      }
    }
  }

  private static byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws Exception
  {
    paramArrayOfByte1 = new SecretKeySpec(paramArrayOfByte1, "AES");
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(1, paramArrayOfByte1, new IvParameterSpec(new byte[localCipher.getBlockSize()]));
    return localCipher.doFinal(paramArrayOfByte2);
  }

  public static String fromHex(String paramString)
  {
    return new String(toByte(paramString));
  }

  private static byte[] getRawKey(byte[] paramArrayOfByte)
    throws Exception
  {
    KeyGenerator localKeyGenerator = KeyGenerator.getInstance("AES");
    SecureRandom localSecureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
    localSecureRandom.setSeed(paramArrayOfByte);
    localKeyGenerator.init(128, localSecureRandom);
    return localKeyGenerator.generateKey().getEncoded();
  }

  public static String getSeed()
  {
    String str1 = new String();
    int i = 0;
    while (true)
    {
      if (i >= str.length() - 1)
        return str1;
      str1 = str1 + str.charAt(i);
      i += 4;
    }
  }

  public static byte[] toByte(String paramString)
  {
    int j = paramString.length() / 2;
    byte[] arrayOfByte = new byte[j];
    int i = 0;
    while (true)
    {
      if (i >= j)
        return arrayOfByte;
      arrayOfByte[i] = Integer.valueOf(paramString.substring(i * 2, i * 2 + 2), 16).byteValue();
      i += 1;
    }
  }

  public static String toHex(String paramString)
  {
    return toHex(paramString.getBytes());
  }

  public static String toHex(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return "";
    StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 2);
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfByte.length)
        return localStringBuffer.toString();
      appendHex(localStringBuffer, paramArrayOfByte[i]);
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.deviceID.SecurityUtils
 * JD-Core Version:    0.6.2
 */
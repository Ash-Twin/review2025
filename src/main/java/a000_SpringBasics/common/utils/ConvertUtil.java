package a000_SpringBasics.common.utils;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * @author Chenyu Liu
 * @since 3/11/25 Tuesday
 **/

public class ConvertUtil {


    /**
     * 将 UUID 转换为 16 字节的二进制数组
     *
     * @param uuid 要转换的 UUID
     * @return 16 字节的二进制数组
     */
    public static byte[] uuidToBinary16(UUID uuid) {
        // 创建一个容量为 16 字节的 ByteBuffer
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        // 将 UUID 的高 64 位放入 ByteBuffer
        bb.putLong(uuid.getMostSignificantBits());
        // 将 UUID 的低 64 位放入 ByteBuffer
        bb.putLong(uuid.getLeastSignificantBits());
        // 返回 ByteBuffer 中的字节数组
        return bb.array();
    }

    /**
     * 将 16 字节的二进制数组转换为 UUID
     *
     * @param bytes 16 字节的二进制数组
     * @return 转换后的 UUID
     */
    public static UUID binary16ToUuid(byte[] bytes) {
        // 创建一个包含传入字节数组的 ByteBuffer
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        // 从 ByteBuffer 中读取高 64 位
        long mostSignificantBits = bb.getLong();
        // 从 ByteBuffer 中读取低 64 位
        long leastSignificantBits = bb.getLong();
        // 根据高 64 位和低 64 位创建 UUID
        return new UUID(mostSignificantBits, leastSignificantBits);
    }
}

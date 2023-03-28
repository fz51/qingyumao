package cn.qingyumao.scaffold.ddd.repository;

import org.springframework.core.GenericTypeResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AggregateLoaderHolder {

    private static final Map<BidAndAr, AggregateLoader<?, ?>> CACHE = new HashMap<>();


    public static <BID, AR> AggregateLoader<BID, AR> getAggregateLoader(Class<BID> bidClazz, Class<AR> arClazz) {

        return (AggregateLoader<BID, AR>) CACHE.get(new BidAndAr(bidClazz, arClazz));
    }

    public static <BID, AR> void setAggregateLoader(AggregateLoader<BID, AR> postProcessor) {
        Class<?> bidClazz = Object.class;
        Class<?> arClazz = Object.class;
        Class<?>[] aggregateGeneratorGenericClasses;
        if (postProcessor instanceof AggregateLoaderMethodAdapter) {
            aggregateGeneratorGenericClasses = ((AggregateLoaderMethodAdapter) postProcessor).getGeneratorGenericClasses();
            bidClazz = aggregateGeneratorGenericClasses[0];
            arClazz = aggregateGeneratorGenericClasses[1];
        } else {
            aggregateGeneratorGenericClasses = GenericTypeResolver.resolveTypeArguments(postProcessor.getClass(), AggregateLoader.class);
            if (aggregateGeneratorGenericClasses != null) {
                bidClazz = aggregateGeneratorGenericClasses[0];
                arClazz = aggregateGeneratorGenericClasses[1];
            }
        }
        final BidAndAr key = new BidAndAr(bidClazz, arClazz);
        final AggregateLoader<?, ?> existed = CACHE.get(key);
        if (existed == null) {
            CACHE.put(key, postProcessor);
        } else {
            throw new IllegalStateException(bidClazz.getName() + " 存在多个加载器");
        }
    }

    static class BidAndAr {
        private final Class<?> bidClazz;
        private final Class<?> arClazz;

        BidAndAr(Class<?> bidClazz, Class<?> arClazz) {
            this.bidClazz = bidClazz;
            this.arClazz = arClazz;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BidAndAr bidAndAr = (BidAndAr) o;

            if (!Objects.equals(bidClazz, bidAndAr.bidClazz)) return false;
            return Objects.equals(arClazz, bidAndAr.arClazz);
        }

        @Override
        public int hashCode() {
            int result = bidClazz != null ? bidClazz.hashCode() : 0;
            result = 31 * result + (arClazz != null ? arClazz.hashCode() : 0);
            return result;
        }
    }
}

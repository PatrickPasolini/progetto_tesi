package it.unibs.domain;

import java.util.*;
import java.util.Map.Entry;

/**
 * Rappresenta una mappa annidata che associa due chiavi a un valore.
 * @param <K1> il tipo della prima chiave
 * @param <K2> il tipo della seconda chiave
 * @param <V> il tipo del valore associato alle chiavi
 */
public class NestedMap<K1, K2, V> implements Iterable<Map.Entry<K1, Map<K2, V>>> {
    private final Map<K1, Map<K2, V>> map;

    /**
     * Costruttore della classe NestedMap che inizializza una mappa vuota.
     */
    public NestedMap() {
        this.map = new HashMap<>();
    }

    /**
     * Inserisce un valore nella mappa associato alle due chiavi specificate
     * @param key1 la prima chiave
     * @param key2 la seconda chiave
     * @param value il valore da inserire
     * @since 1
     */
    public void put(K1 key1, K2 key2, V value) {
        if (!map.containsKey(key1)) {
            map.put(key1, new HashMap<>());
        }
        
        map.get(key1).put(key2, value);
    }

    /**
     * Restituisce il valore associato alle due chiavi specificate
     * @param key1 la prima chiave
     * @param key2 la seconda chiave
     * @return il valore associato alle chiavi, oppure null se non esiste un valore per le chiavi specificate
     * @since 1
     */
    public V get(K1 key1, K2 key2) {
        if (map.containsKey(key1)) {
            return map.get(key1).get(key2);
        }
        
        return null;
    }
    
    /**
     * Restituisce la mappa associata alla prima chiave specificata
     * @param key1 la chiave di cui si vuole ottenere la mappa associata
     * @return la mappa associata alla chiave specificata, oppure null se non esiste una mappa per la chiave specificata
     * @since 1
     */
    public Map<K2, V> get(K1 key1) {
        if (map.containsKey(key1)) {
            return map.get(key1);
        }
        
        return null;
    }
    
    /**
     * Restituisce un insieme di tutte le chiavi di primo livello presenti nella mappa
     * @return un insieme di tutte le chiavi di primo livello
     * @since 1
     */
    public Set<K1> keySet() {
    	return map.keySet();
    }

    /**
     * Verifica se la mappa contiene una coppia chiave-valore associata alle due chiavi specificate
     * @param key1 la prima chiave
     * @param key2 la seconda chiave
     * @return true se la mappa contiene la coppia chiave-valore, altrimenti false
     * @since 1
     */
    public boolean containsKey(K1 key1, K2 key2) {
    	if(map.containsKey(key1)) {
    		return map.get(key1).containsKey(key2);
    	}
    	
    	return false;
    }

    /**
     * Restituisce un iteratore sugli elementi presenti nella nestedMap
     * @return un iteratore sugli elementi presenti nella nestedMap
     * @since 1
     */
	@Override
	public Iterator<Entry<K1, Map<K2, V>>> iterator() {
		return new NestedMapIterator();
	}
	
	/**
	 * NestedMapIterator fornisce un iteratore sugli elementi presenti nella nestedMap
	 * @since 1
	 */
	private class NestedMapIterator implements Iterator<Map.Entry<K1, Map<K2, V>>> {
        private final Iterator<Map.Entry<K1, Map<K2, V>>> outerIterator;
        private Iterator<Map.Entry<K2, V>> innerIterator;

        /**
         * Costruttore di NestedMapIterator.
         * Inizializza l'iteratore esterno con l'insieme di entry della mappa nidificata,
         * e se ci sono elementi, inizializza l'iteratore interno con l'insieme di entry
         * della prima entry della mappa
         * @since 1
         */
        public NestedMapIterator() {
            this.outerIterator = map.entrySet().iterator();
            if (outerIterator.hasNext()) {
                innerIterator = outerIterator.next().getValue().entrySet().iterator();
            }
        }

        /**
         * Restituisce true se l'iterazione ha ancora elementi
         * @return true se l'iterazione ha ancora elementi
         * @since 1
         */
        @Override
        public boolean hasNext() {
            return (innerIterator != null && innerIterator.hasNext()) || outerIterator.hasNext();
        }

        /**
         * Restituisce la prossima entry nell'iterazione
         * @return la prossima entry nell'iterazione
         * @throws NoSuchElementException se l'iterazione non ha più elementi
         * @since 1
         */
        @Override
        public Map.Entry<K1, Map<K2, V>> next() {
        	if (innerIterator != null && innerIterator.hasNext()) {
                Map.Entry<K1, Map<K2, V>> outerEntry = outerIterator.next();
                return Map.entry(outerEntry.getKey(), outerEntry.getValue());
            }            
        	if (!outerIterator.hasNext()) {
                throw new NoSuchElementException();
            }
            Map.Entry<K1, Map<K2, V>> entry = outerIterator.next();
            innerIterator = entry.getValue().entrySet().iterator();
            return entry;
        }
    }
    
	/**
	 * Rimuove l'insieme di coppie chiave-valore associato alla chiave specificata
	 * @param key1 la chiave di primo livello da rimuovere
	 * @since 1
	 */
    public void remove(K1 key1) {
    	map.remove(key1);
    }
    
    /**
     * Rimuove il valore associato alle due chiavi specificate
     * @param key1 la prima chiave
     * @param key2 la seconda chiave
     * @since 1
     */
    public void remove(K1 key1, K2 key2) {
        if (map.containsKey(key1)) {
        	map.get(key1).remove(key2);
        }
    }

    /**
     * Restituisce una collezione contenente tutti i valori della mappa
     * @return collezione di valori della mappa
     * @since 1
     */
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (Map<K2, V> innerMap : map.values()) {
            values.addAll(innerMap.values());
        }
        return values;
    }
    
    /**
     * Verifica se la mappa è vuota
     * @return true se la mappa è vuota, altrimenti false
     * @since 1
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K1, Map<K2, V>> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
    
}
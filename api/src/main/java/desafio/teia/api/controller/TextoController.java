package desafio.teia.api.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("palavras")
public class TextoController {

    @PostMapping
    public void cadastrar(@RequestBody String jsonStr){
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonStr);

            for (Object key : json.keySet()) {
                String chave = (String) key;
                String valor = (String) json.get(chave);

                System.out.println("Verificando se '" + valor + "' é um palíndromo:");
                if (isPalindromo(valor)) {
                    System.out.println(chave + ": " + valor + " é um palíndromo!");
                } else {
                    System.out.println(chave + ": " + valor + " não é um palíndromo.");
                }

                Map<Character, Integer> countMap = countOccurrences(valor);
                System.out.println("Número de ocorrências de cada caractere em '" + valor + "':");
                for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPalindromo(String palavra) {
        int inicio = 0;
        int fim = palavra.length() - 1;

        while (inicio < fim) {
            if (palavra.charAt(inicio) != palavra.charAt(fim)) {
                return false;
            }
            inicio++;
            fim--;
        }

        return true;
    }

    public static Map<Character, Integer> countOccurrences(String palavra) {
        Map<Character, Integer> countMap = new HashMap<>();

        for (char c : palavra.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        return countMap;

    }


}

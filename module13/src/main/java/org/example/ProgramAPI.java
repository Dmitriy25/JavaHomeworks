package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.stream.Collectors;
import java.io.FileWriter;
import java.io.Writer;
public class ProgramAPI {

    public static Optional<User> createUser(User user) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI("https://jsonplaceholder.typicode.com/users/");
        String jsonUser = new Gson().toJson(user);
        HttpRequest httpRequest = HttpRequest.newBuilder(uri)
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonUser))
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        User userResponse = new Gson().fromJson(httpResponse.body(), User.class);
        return Optional.of(userResponse);
    }

    public static Optional<User> updateUser(User user) throws URISyntaxException, IOException, InterruptedException {
        String formattedLink = MessageFormat
                .format("https://jsonplaceholder.typicode.com/users/{0}",user.getId());
        URI uri = new URI(formattedLink);
        String jsonUser = new Gson().toJson(user);
        HttpRequest httpRequest = HttpRequest.newBuilder(uri)
                .headers("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonUser))
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        User userResponse = new Gson().fromJson(httpResponse.body(), User.class);
        return Optional.of(userResponse);
    }

    public static boolean deleteUser(int userId) throws URISyntaxException, IOException, InterruptedException {
        String formattedLink = MessageFormat
                .format("https://jsonplaceholder.typicode.com/users/{0}", userId);
        URI uri = new URI(formattedLink);
        HttpRequest httpRequest = HttpRequest.newBuilder(uri)
                .DELETE()
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (httpResponse.statusCode() / 100 == 2) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static List<User> getAllUsers() throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI("https://jsonplaceholder.typicode.com/users/");
        HttpRequest httpRequest = HttpRequest.newBuilder(uri)
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        List<User> userList = new ArrayList<>();
        if (httpResponse.statusCode() == 200){
            Type type = TypeToken.getParameterized(List.class, User.class).getType();
            userList.addAll(new Gson().fromJson(httpResponse.body(), type));
        }
        return userList;
    }

    public static Optional<User> getUserById(int userId) throws URISyntaxException, IOException, InterruptedException {
        String formattedLink = MessageFormat
                .format("https://jsonplaceholder.typicode.com/users/{0}", userId);
        URI uri = new URI(formattedLink);
        HttpRequest httpRequest = HttpRequest.newBuilder(uri)
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if(httpResponse.statusCode() == 200) {
            User userResponse = new Gson().fromJson(httpResponse.body(), User.class);
            return Optional.of(userResponse);
        }
        return Optional.empty();
    }

    public static List<User> getUserByUsername(String username) throws URISyntaxException, IOException, InterruptedException {
        String formattedLink = MessageFormat
                .format("https://jsonplaceholder.typicode.com/users?username={0}", username);
        URI uri = new URI(formattedLink);
        HttpRequest httpRequest = HttpRequest.newBuilder(uri)
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        List<User> userList = new ArrayList<>();
        if (httpResponse.statusCode() == 200){
            Type type = TypeToken.getParameterized(List.class, User.class).getType();
            userList.addAll(new Gson().fromJson(httpResponse.body(), type));
        }
        return userList;
    }

    private static Optional<Post> getLastPostByUser(int userId) throws IOException, InterruptedException, URISyntaxException {
        String formattedLink = MessageFormat
                .format("https://jsonplaceholder.typicode.com/users/{0}/posts", userId);
        URI uri = new URI(formattedLink);
        HttpRequest httpRequest = HttpRequest.newBuilder(uri)
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        List<Post> postsList = new ArrayList<>();
        if (httpResponse.statusCode() == 200){
            Type type = TypeToken.getParameterized(List.class, Post.class).getType();
            postsList.addAll(new Gson().fromJson(httpResponse.body(), type));
            return postsList.stream()
                    .max(Comparator.comparingInt(Post::getId));
        }
        return Optional.empty();
    }
    public static List<Comment> getCommentsOfUsersLastPost(int userId) throws IOException, InterruptedException, URISyntaxException {
        Optional<Post> lastPostByUser = getLastPostByUser(userId);
        if (lastPostByUser.isPresent()) {
            return getCommentsByPost(userId, lastPostByUser.get().getId());
        }
        return Collections.emptyList();
    }
    private static List<Comment> getCommentsByPost(int userId, int postId) throws IOException, InterruptedException, URISyntaxException {
        String formattedLink = MessageFormat
                .format("https://jsonplaceholder.typicode.com/posts/{0}/comments", postId);
        URI uri = new URI(formattedLink);
        HttpRequest httpRequest = HttpRequest.newBuilder(uri)
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        List<Comment> commentsList = new ArrayList<>();
        if (httpResponse.statusCode() == 200) {
            Type type = TypeToken.getParameterized(List.class, Comment.class).getType();
            commentsList.addAll(new Gson().fromJson(httpResponse.body(), type));

            commentsToJsonFile(userId, postId, commentsList);
        }
        return commentsList;
    }

    private static void commentsToJsonFile(int userId, int postId, List<Comment> commentList) {
        String jsonFile = MessageFormat.format("user-{0}-post-{1}-comments.json",
                userId, postId);
        try (Writer fileWriter = new FileWriter(jsonFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(commentList, fileWriter);
            System.out.println("\nСтворений файл має назву " + jsonFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Todo> getUncompletedTasks(int userId) throws URISyntaxException, IOException, InterruptedException {
        String formattedLink = MessageFormat
                .format("https://jsonplaceholder.typicode.com/users/{0}/todos", userId);
        URI uri = new URI(formattedLink);
        HttpRequest httpRequest = HttpRequest.newBuilder(uri)
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        List<Todo> todosList = new ArrayList<>();
        if (httpResponse.statusCode() == 200){
            Type type = TypeToken.getParameterized(List.class, Todo.class).getType();
            todosList.addAll(new Gson().fromJson(httpResponse.body(), type));
        }
        return todosList.stream()
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList());
    }
}
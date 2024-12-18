https://github.com/user-attachments/assets/9e54a55b-8f5d-4ee3-80b1-8ecfb0adddd0


### App Description
This app is an anime-themed application designed to provide users with detailed information about various anime titles and showcase trending anime. It leverages modern Android development practices to create a smooth and visually appealing user experience. Key features include viewing detailed anime information, browsing trending anime, and using shared element transitions for seamless navigation.

### Key Functionalities
1. **Anime Detail Screen**:
   - Displays detailed information about selected anime, including cover image, title, start date, average rating, and synopsis.
   - Utilizes shared element transitions for smooth animations between screens.
   - Handles loading and error states gracefully.

2. **Trending Anime Screen**:
   - Shows a list of trending anime titles with their images, ratings, and synopses.
   - Allows users to click on anime cards to view detailed information.
   - Uses animations to enhance user experience and display content dynamically.

3. **Reusable Components**:
   - `DisplayStatusOfResponse`: Displays a loading indicator or error message based on the app's state.
   - `AnimeCard`: Represents individual anime items in a list, showing the poster image, title, rating, and a short synopsis.
   - `DisplayAnimatedContent`: Handles the animated display of anime content, ensuring a responsive and engaging UI.

### Tech Stack
1. **Programming Languages**:
2. **Frameworks and Libraries**:
   - **Jetpack Compose**: Used for building declarative UIs in a modern, efficient way.
   - **Hilt**: For dependency injection, simplifying code and improving testability.
   - **Coroutine**: For asynchronous programming, ensuring smooth and responsive UI interactions.
   - **AsyncImage**: For loading images asynchronously in the UI.
   - **Shared Element Transitions**: For creating smooth transitions between UI elements during navigation.


# AppImage (SIApp)

## Overview

The Image Search App is an Android application built using Kotlin. This app allows users to search for images on Google based on a keyword. It displays the images in a RecyclerView, with shared element transitions between the list and detail views for a smooth and visually appealing user experience. The app also utilizes pagination to load images efficiently and caches data to optimize performance.

## Features

- **Search Images**: Search for images on Google using a keyword.
- **RecyclerView Display**: Display search results in a RecyclerView.
- **Shared Element Transitions**: Smooth transitions between list items and detail view.
- **Pagination**: Load images in pages to improve performance and reduce data usage.
- **Data Caching**: Cache data to optimize the application and reduce load times.

## Screenshots

https://github.com/Hissatsu265/AppImage/assets/109668965/b9cb732a-45e9-433c-9e88-2fe6dd17ba86

https://github.com/Hissatsu265/AppImage/assets/109668965/e0bec5a9-7816-44bf-9f49-9af651a69116

## Installation

To use this project, follow these steps:

1. **Clone the repository:**

    ```sh
    git clone https://github.com/Hissatsu265/AppImage.git
    ```

2. **Open the project in Android Studio:**

    Open Android Studio, select "Open an existing project," and navigate to the cloned repository.

3. **Build the project:**

    Allow Android Studio to build the project and download necessary dependencies.

4. **Run the app:**

    Connect an Android device or start an emulator, then run the app from Android Studio.
## Usage

1. **Search for Images:**

    - Enter a keyword in the search bar.
    - Press the search button to fetch images related to the keyword.

2. **View Images:**

    - Browse through the search results displayed in the RecyclerView.
    - Click on an image to view it in the full screen view with a smooth shared element transition.
    - Click on a button "Open Source" it will open the original source page in an external browser.

## Dependencies

This project uses the following dependencies:

- **Retrofit**: For network requests and API interactions.
- **Glide**: For image loading and caching.
- **RecyclerView**: For displaying the list of images.
- **Paging**: For implementing pagination.
- **ViewModel and LiveData**: For managing UI-related data lifecycle.

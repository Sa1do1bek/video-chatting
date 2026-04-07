# Deployment Instructions for Video Chatting App

## Deploying to Render

1. **Create a Render account** at [render.com](https://render.com).
2. **Create a new web service**:
   - Select "Web Service" from the dashboard.
   - Link your GitHub repository by selecting the `video-chatting` repository.
   - Choose the branch you wish to deploy (e.g., main).
3. **Configure build and start commands** as needed:
   - Build Command: e.g., `npm install`
   - Start Command: e.g., `npm run start`
4. **Set the environment variables** required for the application (see below).

## Setting Up Environment Variables

1. Go to the **Settings** of your service in Render.
2. Under the **Environment** section, add the following variables:
   - `DATABASE_URL`: Your database connection string.
   - `API_KEY`: Your API key (if applicable).
   - Any other environment variables your application requires.

## Local Development Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Sa1do1bek/video-chatting.git
   cd video-chatting
   ```
2. **Install dependencies**:
   ```bash
   npm install
   ```
3. **Set up your environment variables** in a `.env` file in the root directory:
   ```plaintext
   DATABASE_URL=your_database_url
   API_KEY=your_api_key
   ```
4. **Run the application** locally:
   ```bash
   npm run dev
   ```

const redirectUrl = (app: string) => {
  const redirectUri = "https://bookrecommendation.website";
  return `http://localhost:8080/oauth2/authorize/${app}?redirect_uri=${redirectUri}`;
};

export default redirectUrl;

const redirectUrl = (app: string) => {
  const redirectUri = "https://bookrecommendation.website";
  return `https://bookrecommendation.website/oauth2/authorize/${app}?redirect_uri=${redirectUri}`;
};

export default redirectUrl;

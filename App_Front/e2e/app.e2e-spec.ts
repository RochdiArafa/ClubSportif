import { SportAppPage } from './app.po';

describe('sport-app App', function() {
  let page: SportAppPage;

  beforeEach(() => {
    page = new SportAppPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
